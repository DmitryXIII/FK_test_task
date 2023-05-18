package ru.avacodo.fktesttask.ui.core

interface ViewStateOwner<T> {
    fun onStartLoadingAction()
    fun onSuccessAction(result: T)
    fun onErrorAction(error: String)
}