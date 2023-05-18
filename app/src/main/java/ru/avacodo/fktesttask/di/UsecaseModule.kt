package ru.avacodo.fktesttask.di

import org.koin.dsl.module
import ru.avacodo.fktesttask.domain.model.FitDataMapper
import ru.avacodo.fktesttask.domain.model.ModelsMapper
import ru.avacodo.fktesttask.domain.usecase.lessons.GetFitDataUsecase
import ru.avacodo.fktesttask.domain.usecase.lessons.GetFitDataUsecaseImpl
import ru.avacodo.fktesttask.domain.usecase.lessons.TimeDurationCalc
import ru.avacodo.fktesttask.domain.usecase.lessons.TimeDurationCalculator

val usecaseModule = module {
    single<GetFitDataUsecase> { GetFitDataUsecaseImpl(repository = get(), modelsMapper = get()) }
    single<TimeDurationCalc> { TimeDurationCalculator() }
    single<ModelsMapper> { FitDataMapper(durationCalc = get()) }
}