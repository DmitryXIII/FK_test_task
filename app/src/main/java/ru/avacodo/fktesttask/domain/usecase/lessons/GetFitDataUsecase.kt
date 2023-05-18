package ru.avacodo.fktesttask.domain.usecase.lessons

import ru.avacodo.fktesttask.domain.model.FitData

interface GetFitDataUsecase {
    suspend fun getFitData(): List<FitData>
}