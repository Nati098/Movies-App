package ru.geekbrains.filmsapp.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.geekbrains.filmsapp.model.apiservice.WebApiService
import ru.geekbrains.filmsapp.model.data.Account
import ru.geekbrains.filmsapp.model.data.Genre
import ru.geekbrains.filmsapp.model.data.Movie

class RepositoryImpl : Repository {

    override fun getTrendingFromServer(mediaType: String, timeWindow: String) =
        WebApiService.getTrending(mediaType, timeWindow)

    override fun getTopRatedFromServer() =
        WebApiService.getTopRated()

    override fun getGenresFromServer(): List<Genre> {
//        TODO("Not yet implemented")
        return ArrayList()
    }

    override fun getMoviesListFromServer(): List<Movie> {
        TODO("Not yet implemented")
    }

    override fun getFavouriteMoviesFromServer(accountId: Int): List<Movie> {
//        TODO("Not yet implemented")
        return ArrayList()
    }

    override fun getMovieDetailsFromServer(movieId: Int): Movie {
//        TODO("Not yet implemented")
        return Movie()
    }

    override fun getAccountFromServer(): Account {
//        TODO("Not yet implemented")
        return Account()
    }
}
