package ru.geekbrains.filmsapp.model.apiservice

import android.os.Handler
import android.os.Parcelable
import android.util.Log
import androidx.viewbinding.BuildConfig
import com.google.gson.Gson
import ru.geekbrains.filmsapp.model.data.Trend
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.MalformedURLException
import java.net.URL
import java.util.stream.Collectors
import javax.net.ssl.HttpsURLConnection

object WebApiService {
    private val TAG = javaClass.canonicalName

    fun getTrending(mediaType: String, timeWindow: String, listener: LoaderListener<Trend>) {
        val url = "https://api.themoviedb.org/3/trending/${mediaType}/${timeWindow}"
        executeGet(url, listener)
    }

    fun getTopRated(listener: LoaderListener<Trend>) {
        val url = "https://api.themoviedb.org/3/movie/top_rated"
        executeGet(url, listener)
    }

    private inline fun <reified P: Parcelable> executeGet(url: String, listener: LoaderListener<P>) {
        try {
            val uri = URL(url)
            val handler = Handler()
            Thread(Runnable {
                lateinit var urlConnection: HttpsURLConnection
                try {
                    urlConnection = uri.openConnection() as HttpsURLConnection
                    urlConnection.requestMethod = "GET"
                    urlConnection.addRequestProperty("api_key", BuildConfig.TMD_API_KEY)
                    urlConnection.readTimeout = 10000
                    val bufferedReader =
                        BufferedReader(InputStreamReader(urlConnection.inputStream))

                    val trendDataModel: P =
                        Gson().fromJson(getLines(bufferedReader), P::class.java)
                    handler.post { listener.onLoaded(trendDataModel) }
                } catch (e: Exception) {
                    Log.e(TAG, "Fail connection", e)
                    e.printStackTrace()
                    listener.onFailed(e)
                } finally {
                    urlConnection.disconnect()
                }
            }).start()
        } catch (e: MalformedURLException) {
            Log.e(TAG, "Fail URI", e)
            e.printStackTrace()
            listener.onFailed(e)
        }
    }

    private fun getLines(reader: BufferedReader): String {
        return reader.lines().collect(Collectors.joining("\n"))
    }


    interface LoaderListener<P: Parcelable> {
        fun onLoaded(data: P)
        fun onFailed(throwable: Throwable)
    }

}