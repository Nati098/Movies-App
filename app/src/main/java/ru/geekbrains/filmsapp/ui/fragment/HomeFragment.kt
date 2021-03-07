package ru.geekbrains.filmsapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import ru.geekbrains.filmsapp.R
import ru.geekbrains.filmsapp.databinding.FragmentHomeBinding
import ru.geekbrains.filmsapp.model.data.Trend
import ru.geekbrains.filmsapp.viewmodel.viewstate.HomeViewState
import ru.geekbrains.filmsapp.viewmodel.vm.HomeViewModel

class HomeFragment : BaseFragment<Trend?, HomeViewState, FragmentHomeBinding>() {

    override val viewModel: HomeViewModel by lazy { ViewModelProvider(this).get(HomeViewModel::class.java) }
    override val layoutRes: Int = R.layout.fragment_home
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentHomeBinding
        get() = TODO("Not yet implemented")


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.getTrendingFromRemote()
    }

    override fun bindView(view: View) {

    }

    private fun setData(trend: Trend) {

    }

    companion object {
        fun newInstance() = HomeFragment()
    }
}