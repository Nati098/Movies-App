package ru.geekbrains.filmsapp.viewmodel.vm

import android.content.Context
import android.content.Intent
import androidx.lifecycle.MutableLiveData
import ru.geekbrains.filmsapp.model.RepositoryImpl
import ru.geekbrains.filmsapp.model.data.Movie
import ru.geekbrains.filmsapp.ui.ID_EXTRA
import ru.geekbrains.filmsapp.ui.MovieDetailsIntentService
import ru.geekbrains.filmsapp.viewmodel.viewstate.MovieViewState

class MovieViewModel(private val repository: RepositoryImpl,
                      observableData: MutableLiveData<MovieViewState>) : BaseViewModel<Movie?, MovieViewState>(observableData) {

    fun getMovieDetailsFromLocal() = Unit
    fun getMovieDetailsFromRemote(context: Context, id : Int) =
        context.startService(Intent(context, MovieDetailsIntentService::class.java).apply {
            putExtra(ID_EXTRA, id)
        })

//    private fun getDataFromRemote(id : Int) {
//        observableData.value = MovieViewState()
//        // TODO
//        Thread {
//            Thread.sleep(1000)
//            observableData.postValue(MovieViewState(movie = repository.getMovieDetailsFromServer(id)))
//        }.start()
//    }

}