package ru.geekbrains.filmsapp.viewmodel.vm

import androidx.lifecycle.MutableLiveData
import ru.geekbrains.filmsapp.model.ApplicationResult
import ru.geekbrains.filmsapp.model.RepositoryImpl
import ru.geekbrains.filmsapp.model.apiservice.RetrofitApiService
import ru.geekbrains.filmsapp.model.apiservice.WebApiService
import ru.geekbrains.filmsapp.model.data.Trend
import ru.geekbrains.filmsapp.model.data.getLocalTrending
import ru.geekbrains.filmsapp.viewmodel.viewstate.HomeViewState


class HomeViewModel(private val repository: RepositoryImpl = RepositoryImpl(RetrofitApiService()),
                    observableData: MutableLiveData<HomeViewState> = MutableLiveData()) : BaseViewModel<Trend?, HomeViewState>(observableData) {

    fun getTrendingFromLocal() = getLocalTrending()
    fun getTrendingFromRemote(mediaType: String, timeWindow: String) {
        observableData.value = HomeViewState()
        repository.getTrendingFromServer(mediaType, timeWindow).observeForever { t: ApplicationResult? ->
            t?.apply {
                when(t) {
                    is ApplicationResult.Success<*> -> observableData.value = HomeViewState(trends = t.data as? Trend)
                    is ApplicationResult.Error -> observableData.value = HomeViewState(error = t.error)
                }
            } ?: return@observeForever
        }
    }

    fun getRatedFromLocal() = getLocalTrending()
    fun getRatedFromRemote() {
        observableData.value = HomeViewState()
        repository.getTopRatedFromServer().observeForever { t: ApplicationResult? ->
            t?.apply {
                when(t) {
                    is ApplicationResult.Success<*> -> observableData.value = HomeViewState(rated = t.data as? Trend)
                    is ApplicationResult.Error -> observableData.value = HomeViewState(error = t.error)
                }
            } ?: return@observeForever
        }
    }

}
