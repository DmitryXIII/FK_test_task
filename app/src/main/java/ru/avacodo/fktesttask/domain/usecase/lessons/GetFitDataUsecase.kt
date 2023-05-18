package ru.avacodo.fktesttask.domain.usecase.lessons

import ru.avacodo.fktesttask.domain.model.LessonDomain

interface GetFitDataUsecase {
    suspend fun getFitData(): List<LessonDomain>
}