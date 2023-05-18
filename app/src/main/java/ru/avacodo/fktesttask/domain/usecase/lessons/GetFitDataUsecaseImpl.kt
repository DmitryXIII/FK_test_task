package ru.avacodo.fktesttask.domain.usecase.lessons

import ru.avacodo.fktesttask.data.FitDataRepository
import ru.avacodo.fktesttask.domain.model.LessonDomain
import java.time.LocalDate
import java.time.format.DateTimeFormatter

private const val DEFAULT_TAB_NAME = "undefined tab name"
private const val DEFAULT_COACH_NAME = "undefined coach name"
private const val SCHEDULE_IS_EMPTY_ERROR = "Нет занятий в расписании"
private const val FORMATTER_PATTERN = "yyyy-MM-dd"

class GetFitDataUsecaseImpl(
    private val repository: FitDataRepository,
    private val durationCalc: TimeDurationCalc
) : GetFitDataUsecase {
    private val dateFormatter = DateTimeFormatter.ofPattern(FORMATTER_PATTERN)

    override suspend fun getFitData(): List<LessonDomain> {
        val remoteData = repository.getFitData()

        if (remoteData.lessons.isEmpty()) error(SCHEDULE_IS_EMPTY_ERROR)

        return remoteData.lessons.map { lesson ->
            with(lesson) {
                LessonDomain(
                    name = name,
                    date = LocalDate.parse(date, dateFormatter),
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
    }
}