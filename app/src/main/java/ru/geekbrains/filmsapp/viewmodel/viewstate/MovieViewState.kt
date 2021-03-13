package ru.geekbrains.filmsapp.viewmodel.viewstate

import ru.geekbrains.filmsapp.model.data.Movie

class MovieViewState(val movie: Movie? = null, error: Throwable? = null) : BaseViewState<Movie?> (movie, error) {
}