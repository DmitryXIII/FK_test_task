package ru.avacodo.fktesttask.data

import ru.avacodo.fktesttask.domain.GetFitDataUsecase
import ru.avacodo.fktesttask.domain.model.ExerciseDomain

class GetFitDataUsecaseImpl(private val repository: FitDataRepository): GetFitDataUsecase {
    override suspend fun getFitData(): ExerciseDomain {
        TODO("Not yet implemented")
    }
}