package ru.avacodo.fktesttask.data

import ru.avacodo.fktesttask.data.dto.FitData

class FitDataRepositoryImpl(private val dataSource: FitDataApi) : FitDataRepository {
    override suspend fun getFitData(): FitData {
        return dataSource.getFitDataAsync().await()
    }
}