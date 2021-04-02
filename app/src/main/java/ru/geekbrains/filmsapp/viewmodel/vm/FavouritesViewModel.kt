package ru.geekbrains.filmsapp.viewmodel.vm

import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.geekbrains.filmsapp.model.repository.RepositoryImpl
import ru.geekbrains.filmsapp.model.apiservice.RetrofitApiService
import ru.geekbrains.filmsapp.model.convertFavouritesDtoToModel
import ru.geekbrains.filmsapp.model.data.FavouritesDTO
import ru.geekbrains.filmsapp.model.data.Movie
import ru.geekbrains.filmsapp.model.repository.RepositoryLocalImpl
import ru.geekbrains.filmsapp.ui.App
import ru.geekbrains.filmsapp.viewmodel.viewstate.FavouriteViewState

class FavouritesViewModel(private val repository: RepositoryImpl = RepositoryImpl(RetrofitApiService()),
                          private val repositoryLocal: RepositoryLocalImpl = RepositoryLocalImpl(App.getFavouritesDao()),
                          observableData: MutableLiveData<FavouriteViewState> = MutableLiveData()
) : BaseViewModel<List<Movie>?, FavouriteViewState>(observableData) {

    fun getFavouritesFromLocal() {
        observableData.value = FavouriteViewState()
        observableData.value = FavouriteViewState(repositoryLocal.getFavouritesFromDb())
    }

    fun getFavouritesFromRemote(accountId: String) {
        observableData.value = FavouriteViewState()
        repository.getFavouriteMoviesFromServer(accountId, getFavouritesCallback)
    }

    private val getFavouritesCallback = object : Callback<FavouritesDTO> {

        override fun onResponse(call: Call<FavouritesDTO>, response: Response<FavouritesDTO>) {
            val body: FavouritesDTO? = response.body()
            observableData.postValue(
                if (response.isSuccessful && body != null) checkResponse(body)
                else FavouriteViewState(error = Throwable("Server error"))
            )
        }

        override fun onFailure(call: Call<FavouritesDTO>, t: Throwable) {
            observableData.postValue(FavouriteViewState(error = t))
        }

        private fun checkResponse(serverResponse: FavouritesDTO): FavouriteViewState {
            val data = serverResponse.results
            return if (data.isNullOrEmpty()) {
                FavouriteViewState(error = Throwable("Error with data"))
            } else {
                FavouriteViewState(convertFavouritesDtoToModel(serverResponse))
            }
        }
    }


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