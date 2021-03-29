package ru.geekbrains.filmsapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.viewbinding.ViewBinding
import kotlinx.android.synthetic.main.fragment_favourites.view.*
import ru.geekbrains.filmsapp.R
import ru.geekbrains.filmsapp.ui.extension.makeLongSnackbar
import ru.geekbrains.filmsapp.viewmodel.viewstate.BaseViewState
import ru.geekbrains.filmsapp.viewmodel.vm.BaseViewModel

abstract class BaseFragment <T, VS : BaseViewState<T>, VB : ViewBinding> : Fragment() {

    abstract val viewModel: BaseViewModel<T, VS>
    abstract val layoutRes: Int
    protected abstract val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> VB

    private var _binding: VB? = null
    protected val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = bindingInflater.invoke(inflater, container, false)
        bindView(binding.root)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    abstract fun bindView(view: View)

    abstract fun renderLoading()

    open protected fun renderData(data: T) {
        view?.makeLongSnackbar(this::class.java.simpleName)
    }

    open protected fun renderError(error: Throwable) {
        view?.makeLongSnackbar(error.message ?: "Empty error")
    }
}