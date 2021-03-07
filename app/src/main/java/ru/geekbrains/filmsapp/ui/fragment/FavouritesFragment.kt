package ru.geekbrains.filmsapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import ru.geekbrains.filmsapp.R
import ru.geekbrains.filmsapp.databinding.FragmentFavouritesBinding
import ru.geekbrains.filmsapp.model.data.Movie
import ru.geekbrains.filmsapp.model.data.Trend
import ru.geekbrains.filmsapp.viewmodel.viewstate.FavouriteViewState
import ru.geekbrains.filmsapp.viewmodel.vm.FavouritesViewModel

class FavouritesFragment : BaseFragment<List<Movie>?, FavouriteViewState, FragmentFavouritesBinding>() {

    override val viewModel: FavouritesViewModel by lazy { ViewModelProvider(this).get(FavouritesViewModel::class.java) }
    override val layoutRes: Int = R.layout.fragment_favourites
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentFavouritesBinding
        get() = TODO("Not yet implemented")


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.getFavouritesFromLocal()
    }

    override fun bindView(view: View) {

    }

    private fun setData(trend: Trend) {

    }

    companion object {
        fun newInstance() = FavouritesFragment()
    }
}