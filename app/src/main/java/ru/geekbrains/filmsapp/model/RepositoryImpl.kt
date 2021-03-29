package ru.geekbrains.filmsapp.model

import retrofit2.Callback
import ru.geekbrains.filmsapp.model.apiservice.RetrofitApiService
import ru.geekbrains.filmsapp.model.apiservice.WebApiService
import ru.geekbrains.filmsapp.model.data.*

class RepositoryImpl(private val apiService: RetrofitApiService) : Repository {

    override fun getTrendingFromServer(mediaType: String, timeWindow: String) =
        WebApiService.getTrending(mediaType, timeWindow)

    override fun getTopRatedFromServer() =
        WebApiService.getTopRated()


    override fun getFavouriteMoviesFromServer(accountId: String, callback: Callback<FavouritesDTO>) =
        apiService.getFavouriteMovies(accountId, callback)

    override fun getAccountFromServer(callback: Callback<AccountDTO>) =
        apiService.getAccount(callback)


    override fun getGenresFromServer(): List<Genre> {
//        TODO("Not yet implemented")
        return ArrayList()
    }

    override fun getMoviesListFromServer(): List<Movie> {
//        TODO("Not yet implemented")
        return ArrayList()
    }

}
