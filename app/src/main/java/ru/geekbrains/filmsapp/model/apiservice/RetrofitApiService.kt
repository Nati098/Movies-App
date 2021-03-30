package ru.geekbrains.filmsapp.model.apiservice

import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.geekbrains.filmsapp.BuildConfig
import ru.geekbrains.filmsapp.model.data.AccountDTO
import ru.geekbrains.filmsapp.model.data.FavouritesDTO

private const val REQUEST_BASE_URL = "https://api.themoviedb.org/3/"

class RetrofitApiService {

    private val builder = Retrofit.Builder()
        .baseUrl(REQUEST_BASE_URL)
        .addConverterFactory(
            GsonConverterFactory.create(
                GsonBuilder().setLenient().create()
            ))
        .client(createOkHttpClient(ResponseInterceptor()))
        .build().create(MovieApi::class.java)

    private fun createOkHttpClient(interceptor: Interceptor): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(interceptor)
        httpClient.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        return httpClient.build()
    }

    fun getAccount(callback: Callback<AccountDTO>) {
        builder.getAccount(BuildConfig.TMD_API_KEY).enqueue(callback)
    }

    fun getFavouriteMovies(id: String, callback: Callback<FavouritesDTO>) {
        builder.getFavouriteMovies(BuildConfig.TMD_API_KEY, accountId = id).enqueue(callback)
    }

}

class ResponseInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        // TODO: make handling of response depends on code
        return chain.proceed(chain.request())
    }
}