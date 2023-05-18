package ru.avacodo.fktesttask.domain.model

data class ExerciseDomain(
    val name: String,
    val date: String,
    val tab: String,
    val startTime: String,
    val endTime: String,
    val coachName: String,
    val place: String
)
