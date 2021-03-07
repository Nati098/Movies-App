package ru.geekbrains.filmsapp.viewmodel


sealed class ApplicationState {

    data class Success<out T>(val data: T) : ApplicationState()

    data class Error(val error: Throwable) : ApplicationState()

    object Loading : ApplicationState()
}