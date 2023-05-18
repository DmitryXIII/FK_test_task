package ru.avacodo.fktesttask.data

import ru.avacodo.fktesttask.data.dto.FitData

interface FitDataRepository {
    suspend fun getFitData(): FitData
}