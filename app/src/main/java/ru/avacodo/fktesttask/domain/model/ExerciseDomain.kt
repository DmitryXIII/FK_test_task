package ru.avacodo.fktesttask.domain.model

import java.time.LocalDate

data class ExerciseDomain(
    val name: String,
    val date: LocalDate,
    val tab: String,
    val startTime: String,
    val endTime: String,
    val coachName: String,
    val place: String
)
