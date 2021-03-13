package ru.geekbrains.filmsapp.viewmodel.vm

import androidx.lifecycle.MutableLiveData
import ru.geekbrains.filmsapp.model.RepositoryImpl
import ru.geekbrains.filmsapp.model.data.Movie
import ru.geekbrains.filmsapp.viewmodel.viewstate.MovieViewState

class MovieViewModel(private val repository: RepositoryImpl,
                      observableData: MutableLiveData<MovieViewState>) : BaseViewModel<Movie?, MovieViewState>(observableData) {

    fun getMovieDetailsFromLocal() = getDataFromRemote()
    fun getMovieDetailsFromRemote() = getDataFromRemote()

    private fun getDataFromRemote() {
        observableData.value = MovieViewState()
        // TODO
        Thread {
            Thread.sleep(1000)
            observableData.postValue(MovieViewState(movie = repository.getMovieDetailsFromServer(0)))
        }.start()
    }

}