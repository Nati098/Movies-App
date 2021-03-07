package ru.geekbrains.filmsapp.viewmodel.vm

import androidx.lifecycle.MutableLiveData
import ru.geekbrains.filmsapp.model.RepositoryImpl
import ru.geekbrains.filmsapp.model.data.Trend
import ru.geekbrains.filmsapp.viewmodel.viewstate.HomeViewState
import java.lang.Thread.sleep

class HomeViewModel(private val repository: RepositoryImpl,
                    observableData: MutableLiveData<HomeViewState>) : BaseViewModel<Trend?, HomeViewState>(observableData) {

    fun getTrendingFromLocal() = getDataFromRemote()
    fun getTrendingFromRemote() = getDataFromRemote()

    private fun getDataFromRemote() {
        observableData.value = HomeViewState()
        // TODO
        Thread {
            sleep(1000)
            observableData.postValue(HomeViewState(trends = repository.getTrendingFromServer()))
        }.start()
    }
}