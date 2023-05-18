package ru.avacodo.fktesttask.data

import ru.avacodo.fktesttask.data.dto.FitDataDto

interface FitDataRepository {
    suspend fun getFitData(): FitDataDto
}