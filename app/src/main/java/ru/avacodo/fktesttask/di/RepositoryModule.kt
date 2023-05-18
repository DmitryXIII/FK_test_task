package ru.avacodo.fktesttask.di

import org.koin.dsl.module
import ru.avacodo.fktesttask.data.FitDataRepository
import ru.avacodo.fktesttask.data.FitDataRepositoryImpl

val repositoryModule = module {
    single<FitDataRepository> { FitDataRepositoryImpl(dataSource = get()) }
}