package ru.avacodo.fktesttask.di

import org.koin.dsl.module
import ru.avacodo.fktesttask.data.GetFitDataUsecaseImpl
import ru.avacodo.fktesttask.domain.GetFitDataUsecase

val usecaseModule = module {
    single<GetFitDataUsecase> { GetFitDataUsecaseImpl(repository = get()) }
}