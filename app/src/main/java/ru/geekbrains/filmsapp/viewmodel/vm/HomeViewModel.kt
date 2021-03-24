package ru.geekbrains.filmsapp.viewmodel.vm

import androidx.lifecycle.MutableLiveData
import ru.geekbrains.filmsapp.model.RepositoryImpl
import ru.geekbrains.filmsapp.model.apiservice.WebApiService
import ru.geekbrains.filmsapp.model.data.Trend
import ru.geekbrains.filmsapp.model.data.getLocalTrending
import ru.geekbrains.filmsapp.viewmodel.viewstate.HomeViewState


class HomeViewModel(private val repository: RepositoryImpl,
                    observableData: MutableLiveData<HomeViewState>) : BaseViewModel<Trend?, HomeViewState>(observableData) {

    fun getTrendingFromLocal() = getLocalTrending()
    fun getTrendingFromRemote(mediaType: String, timeWindow: String, listener: WebApiService.LoaderListener<Trend>) = repository.getTrendingFromServer(mediaType, timeWindow, listener)

    fun getRatedFromLocal() = getLocalTrending()
    fun getRatedFromRemote(listener: WebApiService.LoaderListener<Trend>) = repository.getTopRatedFromServer(listener)

}
