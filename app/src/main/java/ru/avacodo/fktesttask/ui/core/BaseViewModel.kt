package ru.avacodo.fktesttask.ui.core

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel<ResultType> : ViewModel() {
    protected val uiState = MutableLiveData<AppState<ResultType>>()

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        uiState.postValue(AppState.Error(throwable.message.toString()))
    }

    protected fun execute(block: (scope: CoroutineScope, context: CoroutineContext) -> Unit) {
        uiState.postValue(AppState.Loading())
        block.invoke(viewModelScope, Dispatchers.IO + coroutineExceptionHandler)
    }

    fun getData(): LiveData<AppState<ResultType>> = uiState
}