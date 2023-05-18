package ru.avacodo.fktesttask.domain.model

import ru.avacodo.fktesttask.data.dto.FitDataDto

interface ModelsMapper {
    fun mapToDatesList(data: FitDataDto): List<LessonDate>
    fun mapToLessonList(data: FitDataDto): List<LessonDomain>
}