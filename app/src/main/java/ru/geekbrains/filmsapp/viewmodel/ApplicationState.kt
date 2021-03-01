package ru.geekbrains.filmsapp.viewmodel

import ru.geekbrains.filmsapp.model.data.Movie

sealed class ApplicationState {

    data class Success(val movie: Movie) : ApplicationState()

    data class Error(val error: Throwable) : ApplicationState()

    object Loading : ApplicationState()
}