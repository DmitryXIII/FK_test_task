package ru.avacodo.fktesttask.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.avacodo.fktesttask.ui.screens.lessons.LessonListViewModel

val viewModelModule = module {
    viewModel { LessonListViewModel(usecase = get()) }
}