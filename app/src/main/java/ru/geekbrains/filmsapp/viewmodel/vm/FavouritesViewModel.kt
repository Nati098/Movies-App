package ru.geekbrains.filmsapp.viewmodel.vm

import androidx.lifecycle.MutableLiveData
import ru.geekbrains.filmsapp.model.RepositoryImpl
import ru.geekbrains.filmsapp.model.data.Movie
import ru.geekbrains.filmsapp.viewmodel.viewstate.FavouriteViewState

class FavouritesViewModel(private val repository: RepositoryImpl,
                          observableData: MutableLiveData<FavouriteViewState>) : BaseViewModel<List<Movie>?, FavouriteViewState>(observableData) {

    fun getFavouritesFromLocal() = getDataFromRemote()
    fun getFavouritesFromRemote() = getDataFromRemote()

    fun getGenresFromLocal() = getDataFromRemote()
    fun getGenresFromRemote() = getDataFromRemote()

    fun getMoviesByGenreFromLocal(genres: List<Int>) = getDataFromRemote()
    fun getMoviesByGenreFromRemote(genres: List<Int>) = getDataFromRemote()  // /discover/movie with_genres

    private fun getDataFromRemote() {
        observableData.value = FavouriteViewState()  // for ApplicationState=Loading
        // TODO
        Thread {
            Thread.sleep(1000)
            observableData.postValue(FavouriteViewState(repository.getMoviesListFromServer()))
        }.start()
    }
}