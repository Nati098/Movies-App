package ru.geekbrains.filmsapp.model.apiservice

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import ru.geekbrains.filmsapp.model.data.AccountDTO
import ru.geekbrains.filmsapp.model.data.FavouritesDTO

private const val REQUEST_API_KEY = "api_key"

interface MovieApi {

    @GET("/account")
    fun getAccount(
        @Header(REQUEST_API_KEY) token: String
    ) : Call<AccountDTO>

    @GET("/account/{accountId}/favorite/movies")
    fun getFavouriteMovies(
        @Header(REQUEST_API_KEY) token: String,
        @Path("accountId") accountId: String
    ) : Call<FavouritesDTO>
}