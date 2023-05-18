package ru.avacodo.fktesttask.data

import ru.avacodo.fktesttask.data.dto.FitDataDto

class FitDataRepositoryImpl(private val dataSource: FitDataApi) : FitDataRepository {
    override suspend fun getFitData(): FitDataDto {
        return dataSource.getFitDataAsync().await()
    }
}