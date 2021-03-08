package ru.geekbrains.filmsapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.fragment_favourites.*
import ru.geekbrains.filmsapp.R
import ru.geekbrains.filmsapp.databinding.FragmentFavouritesBinding
import ru.geekbrains.filmsapp.model.data.Favourites
import ru.geekbrains.filmsapp.model.data.Movie
import ru.geekbrains.filmsapp.model.data.Trend
import ru.geekbrains.filmsapp.ui.adapter.MovieAdapter
import ru.geekbrains.filmsapp.viewmodel.viewstate.FavouriteViewState
import ru.geekbrains.filmsapp.viewmodel.vm.FavouritesViewModel

class FavouritesFragment : BaseFragment<List<Movie>?, FavouriteViewState, FragmentFavouritesBinding>() {

    override val viewModel: FavouritesViewModel by lazy { ViewModelProvider(this).get(FavouritesViewModel::class.java) }
    override val layoutRes: Int = R.layout.fragment_favourites
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentFavouritesBinding
        get() = TODO("Not yet implemented")

    lateinit var movieAdapter: MovieAdapter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.getFavouritesFromLocal()
    }

    override fun bindView(view: View) {
        recycle_view.layoutManager = GridLayoutManager(context, 3)
        movieAdapter = MovieAdapter(view.context, {})
        recycle_view.adapter = movieAdapter
    }

    private fun setData(data: Favourites?) {

        data?.let {
            fragment_empty.visibility = View.GONE
            movieAdapter.values = it.results
        } ?: showEmptyView()

    }

    private fun showEmptyView() {
        fragment_empty.visibility = View.VISIBLE
        text_empty.setText(R.string.empty_list_message)
    }

    companion object {
        fun newInstance() = FavouritesFragment()
    }
}