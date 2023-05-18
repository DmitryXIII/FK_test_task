package ru.avacodo.fktesttask.domain.model

import java.util.*

data class LessonDomain(
    val name: String,
    val date: Date,
    val tab: String,
    val startTime: String,
    val endTime: String,
    val duration: String,
    val coachName: String,
    val place: String,
    val markerColor: String
) : FitData {
    override fun getType(): FitDataType {
        return FitDataType.LESSON
    }
}
