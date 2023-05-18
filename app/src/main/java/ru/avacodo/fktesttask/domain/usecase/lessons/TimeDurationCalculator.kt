package ru.avacodo.fktesttask.domain.usecase.lessons

import java.text.SimpleDateFormat
import java.util.*

private const val TIME_PARSE_ERROR = "Time parse error"
private const val ONE_MINUTE_IN_MILLIS = 60_000
private const val ONE_HOUR_IN_MILLIS = 3_600_000
private const val HOUR_POSTFIX = " ч."
private const val MINUTE_POSTFIX = " мин."
private const val HOUR_MINUTE_DELIMITER = " "

class TimeDurationCalculator : TimeDurationCalc {

    override fun calcTimeDuration(startTime: String, endTime: String): String {
        val sdf = SimpleDateFormat("HH:mm", Locale.getDefault())
        val startTimeInMillis = sdf.parse(startTime)?.time ?: error(TIME_PARSE_ERROR)
        val endTimeInMillis = sdf.parse(endTime)?.time ?: error(TIME_PARSE_ERROR)

        val durationMillis = endTimeInMillis - startTimeInMillis

        val hours = durationMillis / ONE_HOUR_IN_MILLIS
        val minutes = (durationMillis - hours * ONE_HOUR_IN_MILLIS) / ONE_MINUTE_IN_MILLIS

        return buildString {
            append(hours.toString())
            append(HOUR_POSTFIX)
            append(HOUR_MINUTE_DELIMITER)
            append(minutes.toString())
            append(MINUTE_POSTFIX)
        }
    }
}