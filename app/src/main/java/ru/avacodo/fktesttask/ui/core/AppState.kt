package ru.avacodo.fktesttask.ui.core

sealed class AppState<T> {
    abstract fun handleState(
        stateOwner: ViewStateOwner<T>,
    )

    class Loading<T> : AppState<T>() {
        override fun handleState(stateOwner: ViewStateOwner<T>) {
            stateOwner.onStartLoadingAction()
        }
    }

    class Success<T>(private val result: T) : AppState<T>() {
        override fun handleState(stateOwner: ViewStateOwner<T>) {
            stateOwner.onSuccessAction(result)
        }
    }

    class Error<T>(private val error: String) : AppState<T>() {
        override fun handleState(stateOwner: ViewStateOwner<T>) {
            stateOwner.onErrorAction(error)
        }
    }
}