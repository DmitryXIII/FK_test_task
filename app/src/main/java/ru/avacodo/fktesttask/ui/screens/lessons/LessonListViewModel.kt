package ru.avacodo.fktesttask.ui.screens.lessons

import kotlinx.coroutines.launch
import ru.avacodo.fktesttask.domain.GetFitDataUsecase
import ru.avacodo.fktesttask.domain.model.LessonDomain
import ru.avacodo.fktesttask.ui.core.AppState
import ru.avacodo.fktesttask.ui.core.BaseViewModel

class LessonListViewModel(private val usecase: GetFitDataUsecase) :
    BaseViewModel<List<LessonDomain>>() {

    fun getLessonsList() {
        super.execute { baseScope, baseContext ->
            baseScope.launch(baseContext) {
                uiState.postValue(AppState.Success(usecase.getFitData()))
            }
        }
    }
}


