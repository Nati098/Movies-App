package ru.geekbrains.filmsapp.viewmodel.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.geekbrains.filmsapp.viewmodel.viewstate.BaseViewState

open class BaseViewModel<T, VS : BaseViewState<T>>(protected val observableData: MutableLiveData<VS> ) : ViewModel() {

    fun getLiveData() : LiveData<VS> = observableData


}
