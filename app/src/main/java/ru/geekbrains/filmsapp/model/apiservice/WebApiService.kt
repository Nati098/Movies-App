package ru.geekbrains.filmsapp.model.apiservice

import android.os.Handler
import android.os.Parcelable
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import ru.geekbrains.filmsapp.BuildConfig
import ru.geekbrains.filmsapp.model.ApplicationResult
import ru.geekbrains.filmsapp.model.data.Trend
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.MalformedURLException
import java.net.URL
import java.util.stream.Collectors
import javax.net.ssl.HttpsURLConnection

private const val REQUEST_GET = "GET"
private const val REQUEST_TIMEOUT = 10000
private const val REQUEST_API_KEY = "api_key"

object WebApiService {
    private val TAG = javaClass.canonicalName

    fun getTrending(mediaType: String, timeWindow: String) : LiveData<ApplicationResult> =
        MutableLiveData<ApplicationResult>().apply {
            val url = "https://api.themoviedb.org/3/trending/${mediaType}/${timeWindow}"
            executeGet<Trend>(this, url)
        }

    fun getTopRated() =
        MutableLiveData<ApplicationResult>().apply {
            val url = "https://api.themoviedb.org/3/movie/top_rated"
            executeGet<Trend>(this, url)
        }

    private inline fun <reified P: Parcelable> executeGet(liveData: MutableLiveData<ApplicationResult>, url: String) {
        try {
            val uri = URL(url)
            val handler = Handler()
            Thread(Runnable {
                lateinit var urlConnection: HttpsURLConnection
                try {
                    urlConnection = uri.openConnection() as HttpsURLConnection
                    urlConnection.requestMethod = REQUEST_GET
                    urlConnection.addRequestProperty(REQUEST_API_KEY, BuildConfig.TMD_API_KEY)
                    urlConnection.readTimeout = REQUEST_TIMEOUT
                    val bufferedReader =
                        BufferedReader(InputStreamReader(urlConnection.inputStream))

                    val trendDataModel: P =
                        Gson().fromJson(getLines(bufferedReader), P::class.java)
                    handler.post { liveData.value = ApplicationResult.Success(trendDataModel) }
                } catch (e: Exception) {
                    Log.e(TAG, "Fail connection", e)
                    e.printStackTrace()
                    liveData.value = ApplicationResult.Error(error = e.fillInStackTrace())
                } finally {
                    urlConnection.disconnect()
                }
            }).start()
        } catch (e: MalformedURLException) {
            Log.e(TAG, "Fail URI", e)
            e.printStackTrace()
            liveData.value = ApplicationResult.Error(error = e.fillInStackTrace())
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