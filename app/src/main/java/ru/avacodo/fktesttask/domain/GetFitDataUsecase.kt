package ru.avacodo.fktesttask.domain

import ru.avacodo.fktesttask.domain.model.LessonDomain

interface GetFitDataUsecase {
    suspend fun getFitData(): List<LessonDomain>
}