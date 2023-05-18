package ru.avacodo.fktesttask.domain

import ru.avacodo.fktesttask.domain.model.ExerciseDomain

interface GetFitDataUsecase {
    suspend fun getFitData(): List<ExerciseDomain>
}