package ru.geekbrains.filmsapp.viewmodel.vm

import androidx.lifecycle.MutableLiveData
import ru.geekbrains.filmsapp.model.RepositoryImpl
import ru.geekbrains.filmsapp.model.data.Movie
import ru.geekbrains.filmsapp.viewmodel.viewstate.MovieViewState

class MoviesViewModel(private val repository: RepositoryImpl,
                      observableData: MutableLiveData<MovieViewState>) : BaseViewModel<List<Movie>?, MovieViewState>(observableData) {

    fun getGenresFromLocal() = getDataFromRemote()
    fun getGenresFromRemote() = getDataFromRemote()

    fun getMoviesByGenreFromLocal(genres: List<Int>) = getDataFromRemote()
    fun getMoviesByGenreFromRemote(genres: List<Int>) = getDataFromRemote()  // /discover/movie with_genres

    private fun getDataFromRemote() {
        observableData.value = MovieViewState()  // for ApplicationState=Loading
        // TODO
        Thread {
            Thread.sleep(1000)
            observableData.postValue(MovieViewState(repository.getMoviesListFromServer()))
        }.start()
    }
}