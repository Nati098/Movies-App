package ru.geekbrains.filmsapp.model

import androidx.lifecycle.LiveData
import ru.geekbrains.filmsapp.model.apiservice.WebApiService
import ru.geekbrains.filmsapp.model.data.Account
import ru.geekbrains.filmsapp.model.data.Genre
import ru.geekbrains.filmsapp.model.data.Movie
import ru.geekbrains.filmsapp.model.data.Trend

interface Repository {
    fun getTrendingFromServer(mediaType: String, timeWindow: String, listener: WebApiService.LoaderListener<Trend>) : Trend
    fun getTopRatedFromServer(listener: WebApiService.LoaderListener<Trend>) : Trend
    fun getGenresFromServer() : List<Genre>
    fun getFavouriteMoviesFromServer(accountId: Int): List<Movie>
    fun getMoviesListFromServer() : List<Movie>
    fun getMovieDetailsFromServer(movieId: Int): Movie

    fun getAccountFromServer() : Account
}
