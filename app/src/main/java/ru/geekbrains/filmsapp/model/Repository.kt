package ru.geekbrains.filmsapp.model

import ru.geekbrains.filmsapp.model.data.Genre
import ru.geekbrains.filmsapp.model.data.Movie
import ru.geekbrains.filmsapp.model.data.Trend

interface Repository {
    fun getTrendingFromServer() : Trend
    fun getGenresFromServer() : List<Genre>
    fun getMoviesListFromServer() : List<Movie>
    fun getMovieDetailsFromServer(movieId: Int) : Movie
}
