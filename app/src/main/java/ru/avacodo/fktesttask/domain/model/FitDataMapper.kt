package ru.avacodo.fktesttask.domain.model

import ru.avacodo.fktesttask.data.dto.FitDataDto
import ru.avacodo.fktesttask.domain.usecase.lessons.TimeDurationCalc
import java.text.SimpleDateFormat
import java.util.*

private const val DEFAULT_TAB_NAME = "undefined tab name"
private const val DEFAULT_COACH_NAME = "undefined coach name"
private const val DATE_PATTERN = "yyyy-MM-dd"
private const val DATE_PARSE_ERROR = "date parse error"

class FitDataMapper(private val durationCalc: TimeDurationCalc) : ModelsMapper {
    private val dateFormatter = SimpleDateFormat(DATE_PATTERN, Locale.getDefault())

    override fun mapToDatesList(data: FitDataDto): List<LessonDate> {
        return data.lessons
            .distinctBy { it.date }
            .map { it.date }
            .map {
                LessonDate(
                    date = dateFormatter.parse(it) ?: error(DATE_PARSE_ERROR)
                )
            }
            .sortedBy { it.date }
    }

    override fun mapToLessonList(data: FitDataDto): List<LessonDomain> {
        return data.lessons.map { lesson ->
            with(lesson) {
                LessonDomain(
                    name = name,
                    date = dateFormatter.parse(date) ?: error(DATE_PARSE_ERROR),
                    tab = data.tabs.find { it.id == tab_id }?.name
                        ?: DEFAULT_TAB_NAME,
                    startTime = startTime,
                    endTime = endTime,
                    duration = durationCalc.calcTimeDuration(startTime, endTime),
                    coachName = data.trainers.find { it.id == coach_id }?.full_name
                        ?: DEFAULT_COACH_NAME,
                    place = place,
                    markerColor = color
                )
            }
        }
    }
}