package ru.geekbrains.filmsapp.viewmodel.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.geekbrains.filmsapp.model.RepositoryImpl
import ru.geekbrains.filmsapp.model.data.Genre
import ru.geekbrains.filmsapp.viewmodel.ApplicationState

class MoviesViewModel(private val repository: RepositoryImpl,
                      private val observableData: MutableLiveData<ApplicationState>) : ViewModel() {

    fun getLiveData() : LiveData<ApplicationState> = observableData

    fun getGenresFromLocal() = getDataFromRemote()
    fun getGenresFromRemote() = getDataFromRemote()

    fun getMoviesByGenreFromLocal(genre: Genre) = getDataFromRemote()
    fun getMoviesByGenreFromRemote(genre: Genre) = getDataFromRemote()

    private fun getDataFromRemote() {
        observableData.value = ApplicationState.Loading
        // TODO
        Thread {
            Thread.sleep(1000)
            observableData.postValue(ApplicationState.Success(repository.getMoviesListFromServer()))
        }.start()
    }
}