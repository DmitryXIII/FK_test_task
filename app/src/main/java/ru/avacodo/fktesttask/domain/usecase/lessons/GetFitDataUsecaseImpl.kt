package ru.avacodo.fktesttask.domain.usecase.lessons

import ru.avacodo.fktesttask.data.FitDataRepository
import ru.avacodo.fktesttask.domain.model.*
import java.text.SimpleDateFormat
import java.util.*

private const val TIME_PATTERN = "HH:mm"
private const val SCHEDULE_IS_EMPTY_ERROR = "Нет занятий в расписании"

class GetFitDataUsecaseImpl(
    private val repository: FitDataRepository,
    private val modelsMapper: ModelsMapper
) : GetFitDataUsecase {
    private val timeFormatter = SimpleDateFormat(TIME_PATTERN, Locale.getDefault())

    override suspend fun getFitData(): List<FitData> {
        val remoteData = repository.getFitData()

        if (remoteData.lessons.isEmpty()) error(SCHEDULE_IS_EMPTY_ERROR)

        val dates = modelsMapper.mapToDatesList(remoteData)

        val lessons = modelsMapper.mapToLessonList(remoteData)

        return mutableListOf<FitData>().apply {
            dates.forEach { date ->
                add(date)
                lessons
                    .filter { it.date == date.date }
                    .sortedBy { timeFormatter.parse(it.startTime) }
                    .forEach {
                        add(it)
                    }
            }
        }
    }
}