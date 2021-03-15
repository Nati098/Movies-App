package ru.geekbrains.filmsapp.model

import ru.geekbrains.filmsapp.model.apiservice.WebApiService
import ru.geekbrains.filmsapp.model.data.Account
import ru.geekbrains.filmsapp.model.data.Genre
import ru.geekbrains.filmsapp.model.data.Movie
import ru.geekbrains.filmsapp.model.data.Trend

class RepositoryImpl : Repository {

    override fun getTrendingFromServer(mediaType: String, timeWindow: String, listener: WebApiService.LoaderListener<Trend>): Trend {
        WebApiService.getTrending(mediaType, timeWindow, listener)
        return Trend()
    }

    override fun getTopRatedFromServer(listener: WebApiService.LoaderListener<Trend>): Trend {
        WebApiService.getTopRated(listener)
        return Trend()
    }

    override fun getGenresFromServer(): List<Genre> {
//        TODO("Not yet implemented")
        return ArrayList()
    }

    override fun getMoviesListFromServer(): List<Movie> {
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
