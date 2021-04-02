package ru.geekbrains.filmsapp.viewmodel.vm

import android.content.Context
import android.content.Intent
import androidx.lifecycle.MutableLiveData
import ru.geekbrains.filmsapp.model.repository.RepositoryImpl
import ru.geekbrains.filmsapp.model.apiservice.RetrofitApiService
import ru.geekbrains.filmsapp.model.data.Movie
import ru.geekbrains.filmsapp.ui.ID_EXTRA
import ru.geekbrains.filmsapp.ui.MovieDetailsIntentService
import ru.geekbrains.filmsapp.viewmodel.viewstate.MovieViewState

class MovieViewModel(private val repository: RepositoryImpl = RepositoryImpl(RetrofitApiService()),
                     observableData: MutableLiveData<MovieViewState>) : BaseViewModel<Movie?, MovieViewState>(observableData) {

    fun getMovieDetailsFromLocal() = getDataFromLocal()
    fun getMovieDetailsFromRemote(context: Context, id : Int) {
        observableData.value = MovieViewState()
        context.startService(Intent(context, MovieDetailsIntentService::class.java).apply {
            putExtra(ID_EXTRA, id)
        })
    }


    private fun getDataFromLocal() {
        observableData.value = MovieViewState()
        Thread {
            Thread.sleep(1000)
            observableData.postValue(MovieViewState(
                movie = Movie(550, listOf(18),
                    "Fight Club",
                    "A ticking-time-bomb insomniac and a slippery soap salesman channel primal male aggression into a shocking new form of therapy. Their concept catches on, with underground \"fight clubs\" forming in every town, until an eccentric gets in the way and ignites an out-of-control spiral toward oblivion.",
                    null, popularity = 0.5, adult = false)))
        }.start()
    }

}