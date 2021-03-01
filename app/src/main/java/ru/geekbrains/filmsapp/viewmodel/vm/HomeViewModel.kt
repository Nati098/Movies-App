package ru.geekbrains.filmsapp.viewmodel.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.geekbrains.filmsapp.model.RepositoryImpl
import ru.geekbrains.filmsapp.viewmodel.ApplicationState
import java.lang.Thread.sleep

class HomeViewModel(private val repository: RepositoryImpl,
                    private val observableData: MutableLiveData<ApplicationState>) : ViewModel() {

    fun getLiveData() : LiveData<ApplicationState> = observableData

    fun getTrendingFromLocal() = getDataFromRemote()
    fun getTrendingFromRemote() = getDataFromRemote()

    private fun getDataFromRemote() {
        observableData.value = ApplicationState.Loading
        // TODO
        Thread {
            sleep(1000)
            observableData.postValue(ApplicationState.Success(repository.getTrendingFromServer()))
        }.start()
    }
}