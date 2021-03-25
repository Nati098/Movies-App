package ru.geekbrains.filmsapp.ui

import android.app.IntentService
import android.content.Intent
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ru.geekbrains.filmsapp.model.data.Movie
import ru.geekbrains.filmsapp.model.response.SuccessResponse
import ru.geekbrains.filmsapp.ui.fragment.*
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.reflect.Type
import java.net.MalformedURLException
import java.net.URL
import java.util.stream.Collectors
import javax.net.ssl.HttpsURLConnection


const val ID_EXTRA = "movie_id_extra"
private const val REQUEST_GET = "GET"
private const val REQUEST_TIMEOUT = 10000
private const val REQUEST_API_KEY = "api_key"

class MovieDetailsIntentService(val name: String = "MovieDetailsIntentService") : IntentService(name) {

    private val broadcastIntent = Intent(INTENT_FILTER)

    override fun onHandleIntent(intent: Intent?) {
        if (intent == null) {
            onEmptyIntent()
        }
        else {
            val id = intent.getIntExtra(ID_EXTRA, -1)
            if (id < 0)
                onEmptyData()
            else
                loadDetails(id)
        }
    }


    private fun loadDetails(id: Int) {
        try {
            val uri = URL("https://api.themoviedb.org/3/movie/$id")
            lateinit var urlConnection: HttpsURLConnection
            try {
                urlConnection = uri.openConnection() as HttpsURLConnection
                urlConnection.apply {
                    requestMethod = REQUEST_GET
                    readTimeout = REQUEST_TIMEOUT
                    addRequestProperty(REQUEST_API_KEY, "99e8522ad678a5d83cf57ccf2a340d90")
                }

                val responseType: Type = object : TypeToken<SuccessResponse<List<Movie>>>() {}.type
                val movieResponse: SuccessResponse<Movie> =
                    Gson().fromJson(getLines(BufferedReader(InputStreamReader(urlConnection.inputStream))), responseType)
                onResponse(movieResponse)
            } catch (e: Exception) {
                onErrorRequest(e.message ?: "Empty error")
            } finally {
                urlConnection.disconnect()
            }
        } catch (e: MalformedURLException) {
            onMalformedURL()
        }
    }

    private fun getLines(reader: BufferedReader): String {
        return reader.lines().collect(Collectors.joining("\n"))
    }

    private fun onResponse(response : SuccessResponse<Movie>) {
        response.data?.apply { onSuccess(this) } ?: onEmptyResponse()
    }

    private fun onSuccess(movie : Movie) {
        putResult(ON_SUCCESS_EXTRA)
        broadcastIntent.putExtra(MOVIE_DETAILS_EXTRA, movie)
        LocalBroadcastManager.getInstance(this).sendBroadcast(broadcastIntent)
    }

    private fun onMalformedURL() {
        putResult(MALFORMED_URL_EXTRA)
        LocalBroadcastManager.getInstance(this).sendBroadcast(broadcastIntent)
    }

    private fun onErrorRequest(error: String) {
        putResult(ERROR_REQUEST_EXTRA)
        broadcastIntent.putExtra(ERROR_REQUEST_MSG_EXTRA, error)
        LocalBroadcastManager.getInstance(this).sendBroadcast(broadcastIntent)
    }

    private fun onEmptyData() {
        putResult(EMPTY_DATA_EXTRA)
        LocalBroadcastManager.getInstance(this).sendBroadcast(broadcastIntent)
    }

    private fun onEmptyIntent() {
        putResult(EMPTY_INTENT_EXTRA)
        LocalBroadcastManager.getInstance(this).sendBroadcast(broadcastIntent)
    }

    private fun onEmptyResponse() {
        putResult(EMPTY_RESPONSE_EXTRA)
        LocalBroadcastManager.getInstance(this).sendBroadcast(broadcastIntent)
    }

    private fun putResult(result: String) {
        broadcastIntent.putExtra(LOAD_RESULT_EXTRA, result)
    }
}