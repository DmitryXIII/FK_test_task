package ru.avacodo.fktesttask.domain.usecase.lessons

interface TimeDurationCalc {
    fun calcTimeDuration(startTime: String, endTime: String): String
}