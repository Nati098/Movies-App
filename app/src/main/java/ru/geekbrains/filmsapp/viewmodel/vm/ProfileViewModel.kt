package ru.geekbrains.filmsapp.viewmodel.vm

import androidx.lifecycle.MutableLiveData
import ru.geekbrains.filmsapp.model.RepositoryImpl
import ru.geekbrains.filmsapp.model.data.Account
import ru.geekbrains.filmsapp.viewmodel.viewstate.ProfileViewState

class ProfileViewModel(private val repository: RepositoryImpl,
                       observableData: MutableLiveData<ProfileViewState>) : BaseViewModel<Account?, ProfileViewState>(observableData) {

    fun getAccountFromLocal() = getDataFromRemote()
    fun getAccountFromRemote() = getDataFromRemote()

    private fun getDataFromRemote() {
        observableData.value = ProfileViewState()
        // TODO
        Thread {
            Thread.sleep(1000)
            observableData.postValue(ProfileViewState(repository.getAccountFromServer()))
        }.start()
    }
}