package ru.geekbrains.filmsapp.viewmodel.viewstate

import ru.geekbrains.filmsapp.model.data.Movie

class MovieViewState(val movies: List<Movie>? = null, error: Throwable? = null) : BaseViewState<List<Movie>?> (movies, error)
