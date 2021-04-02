package ru.geekbrains.filmsapp.viewmodel.vm

import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.geekbrains.filmsapp.model.repository.RepositoryImpl
import ru.geekbrains.filmsapp.model.apiservice.RetrofitApiService
import ru.geekbrains.filmsapp.model.convertAccountDtoToModel
import ru.geekbrains.filmsapp.model.data.Account
import ru.geekbrains.filmsapp.model.data.AccountDTO
import ru.geekbrains.filmsapp.viewmodel.viewstate.ProfileViewState

class ProfileViewModel(private val repository: RepositoryImpl = RepositoryImpl(RetrofitApiService()),
                       observableData: MutableLiveData<ProfileViewState>) : BaseViewModel<Account?, ProfileViewState>(observableData) {

    fun getAccountFromLocal() = getDataFromLocal()
    fun getAccountFromRemote() {
        observableData.value = ProfileViewState()
        repository.getAccountFromServer(getAccountCallback)
    }

    private val getAccountCallback = object : Callback<AccountDTO> {
        override fun onResponse(call: Call<AccountDTO>, response: Response<AccountDTO>) {
            val body: AccountDTO? = response.body()
            observableData.postValue(
                if (response.isSuccessful && body != null) checkResponse(body)
                else ProfileViewState(error = Throwable("Server error"))
            )
        }

        override fun onFailure(call: Call<AccountDTO>, t: Throwable) {
            observableData.postValue(ProfileViewState(error = t))
        }

        private fun checkResponse(serverResponse: AccountDTO): ProfileViewState {
            val data = serverResponse.fact
            return if (data?.id == null || data.name.isNullOrEmpty() || data.username.isNullOrEmpty() || data.includeAdult == null) {
                ProfileViewState(error = Throwable("Error with data"))
            } else {
                ProfileViewState(convertAccountDtoToModel(serverResponse))
            }
        }
    }

    private fun getDataFromLocal() {
        observableData.value = ProfileViewState()
        Thread {
            Thread.sleep(1000)
            observableData.postValue(ProfileViewState(account = Account(548, "Emma Bekker", "a.n.onim", true)))
        }.start()
    }
}