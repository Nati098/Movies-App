package ru.geekbrains.filmsapp.model

import androidx.lifecycle.LiveData
import ru.geekbrains.filmsapp.model.data.Account
import ru.geekbrains.filmsapp.model.data.Genre
import ru.geekbrains.filmsapp.model.data.Movie

interface Repository {
    fun getTrendingFromServer(mediaType: String, timeWindow: String) : LiveData<ApplicationResult>
    fun getTopRatedFromServer() : LiveData<ApplicationResult>
    fun getGenresFromServer() : List<Genre>
    fun getFavouriteMoviesFromServer(accountId: Int): List<Movie>
    fun getMoviesListFromServer() : List<Movie>
    fun getMovieDetailsFromServer(movieId: Int): Movie

    fun getAccountFromServer() : Account
}
