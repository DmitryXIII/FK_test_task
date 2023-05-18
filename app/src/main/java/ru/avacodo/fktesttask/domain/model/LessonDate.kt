package ru.avacodo.fktesttask.domain.model

import java.util.*

data class LessonDate(
    val date: Date
) : FitData {
    override fun getType(): FitDataType {
        return FitDataType.DATE
    }
}
