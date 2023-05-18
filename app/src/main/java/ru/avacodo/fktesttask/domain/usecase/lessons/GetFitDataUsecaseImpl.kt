package ru.avacodo.fktesttask.domain.usecase.lessons

import ru.avacodo.fktesttask.data.FitDataRepository
import ru.avacodo.fktesttask.domain.model.FitData
import ru.avacodo.fktesttask.domain.model.LessonDate
import ru.avacodo.fktesttask.domain.model.LessonDomain
import java.text.SimpleDateFormat

private const val DEFAULT_TAB_NAME = "undefined tab name"
private const val DEFAULT_COACH_NAME = "undefined coach name"
private const val SCHEDULE_IS_EMPTY_ERROR = "Нет занятий в расписании"
private const val FORMATTER_PATTERN = "yyyy-MM-dd"

class GetFitDataUsecaseImpl(
    private val repository: FitDataRepository,
    private val durationCalc: TimeDurationCalc
) : GetFitDataUsecase {
    private val dateFormatter = SimpleDateFormat(FORMATTER_PATTERN)

    override suspend fun getFitData(): List<FitData> {
        val remoteData = repository.getFitData()

        if (remoteData.lessons.isEmpty()) error(SCHEDULE_IS_EMPTY_ERROR)

        val dates = remoteData.lessons
            .distinctBy { it.date }
            .map { it.date }
            .map {
                LessonDate(
                    date = dateFormatter.parse(it)
                )
            }
            .sortedBy { it.date }

        val lessons = remoteData.lessons.map { lesson ->
            with(lesson) {
                LessonDomain(
                    name = name,
                    date = dateFormatter.parse(date),
                    tab = remoteData.tabs.find { it.id == tab_id }?.name ?: DEFAULT_TAB_NAME,
                    startTime = startTime,
                    endTime = endTime,
                    duration = durationCalc.calcTimeDuration(startTime, endTime),
                    coachName = remoteData.trainers.find { it.id == coach_id }?.full_name
                        ?: DEFAULT_COACH_NAME,
                    place = place,
                    markerColor = color
                )
            }
        }

        return mutableListOf<FitData>().apply {
            dates.forEach { date ->
                add(date)
                lessons.filter { it.date == date.date }.forEach {
                    add(it)
                }
            }
        }
    }
}