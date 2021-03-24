package ru.geekbrains.filmsapp.model


sealed class ApplicationResult {

    data class Success<out T>(val data: T) : ApplicationResult()

    data class Error(val error: Throwable) : ApplicationResult()

    object Loading : ApplicationResult()
}