package ru.geekbrains.filmsapp.model

import androidx.lifecycle.LiveData
import retrofit2.Callback
import ru.geekbrains.filmsapp.model.data.*

interface Repository {
    // via HttpsURLConnection
    fun getTrendingFromServer(mediaType: String, timeWindow: String) : LiveData<ApplicationResult>
    fun getTopRatedFromServer() : LiveData<ApplicationResult>

    // via Retrofit
    fun getFavouriteMoviesFromServer(accountId: String, callback: Callback<FavouritesDTO>): Unit
    fun getAccountFromServer(callback: Callback<AccountDTO>) : Unit

    fun getGenresFromServer() : List<Genre>
    fun getMoviesListFromServer() : List<Movie>
    fun getMovieDetailsFromServer(movieId: Int): Movie
}
