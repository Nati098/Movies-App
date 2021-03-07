package ru.geekbrains.filmsapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.viewbinding.ViewBinding
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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.getLiveData().observe(viewLifecycleOwner, Observer {
            renderData(it)
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    abstract fun bindView(view: View)

    protected fun renderData(state: VS) {
        Toast.makeText(context, this::class.java.simpleName, Toast.LENGTH_LONG)
    }
}