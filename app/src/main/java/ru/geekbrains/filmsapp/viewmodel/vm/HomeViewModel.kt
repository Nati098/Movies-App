package ru.geekbrains.filmsapp.viewmodel.vm

import androidx.lifecycle.MutableLiveData
import ru.geekbrains.filmsapp.model.ApplicationResult
import ru.geekbrains.filmsapp.model.RepositoryImpl
import ru.geekbrains.filmsapp.model.apiservice.WebApiService
import ru.geekbrains.filmsapp.model.data.Trend
import ru.geekbrains.filmsapp.model.data.getLocalTrending
import ru.geekbrains.filmsapp.viewmodel.viewstate.HomeViewState


class HomeViewModel(private val repository: RepositoryImpl,
                    observableData: MutableLiveData<HomeViewState>) : BaseViewModel<Trend?, HomeViewState>(observableData) {

    fun getTrendingFromLocal() = getLocalTrending()
    fun getTrendingFromRemote(mediaType: String, timeWindow: String) =
        repository.getTrendingFromServer(mediaType, timeWindow).observeForever { t: ApplicationResult? ->
            t?.apply {
                when(t) {
                    is ApplicationResult.Success<*> -> observableData.value = HomeViewState(trends = t.data as? Trend)
                    is ApplicationResult.Error -> observableData.value = HomeViewState(error = t.error)
                }
            } ?: return@observeForever
        }

    fun getRatedFromLocal() = getLocalTrending()
    fun getRatedFromRemote() =
        repository.getTopRatedFromServer().observeForever { t: ApplicationResult? ->
            t?.apply {
                when(t) {
                    is ApplicationResult.Success<*> -> observableData.value = HomeViewState(rated = t.data as? Trend)
                    is ApplicationResult.Error -> observableData.value = HomeViewState(error = t.error)
                }
            } ?: return@observeForever
        }

}
