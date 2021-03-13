package ru.geekbrains.filmsapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import ru.geekbrains.filmsapp.R
import ru.geekbrains.filmsapp.databinding.FragmentFavouritesBinding
import ru.geekbrains.filmsapp.model.data.Favourites
import ru.geekbrains.filmsapp.model.data.Movie
import ru.geekbrains.filmsapp.ui.adapter.MovieAdapter
import ru.geekbrains.filmsapp.ui.extension.createCancelableAlertDialog
import ru.geekbrains.filmsapp.viewmodel.viewstate.FavouriteViewState
import ru.geekbrains.filmsapp.viewmodel.vm.FavouritesViewModel

class FavouritesFragment : BaseFragment<List<Movie>?, FavouriteViewState, FragmentFavouritesBinding>() {

    override val viewModel: FavouritesViewModel by lazy { ViewModelProvider(this).get(FavouritesViewModel::class.java) }
    override val layoutRes: Int = R.layout.fragment_favourites
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentFavouritesBinding
            = { layoutInflater: LayoutInflater, viewGroup: ViewGroup?, b: Boolean -> FragmentFavouritesBinding.inflate(layoutInflater)}

    lateinit var movieAdapter: MovieAdapter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.getFavouritesFromLocal()
    }

    override fun bindView(view: View) {
        binding.recycleView.layoutManager = GridLayoutManager(context, 3)
        movieAdapter = MovieAdapter(view.context, {})
        binding.recycleView.adapter = movieAdapter

        view.context.createCancelableAlertDialog(R.string.bottom_nav_item_favourites)
    }

    private fun setData(data: Favourites?) {

        data?.let {
            binding.fragmentEmpty.visibility = View.GONE
            movieAdapter.values = it.results
        } ?: showEmptyView()

    }

    private fun showEmptyView() {
        binding.fragmentEmpty.visibility = View.VISIBLE
        binding.textEmpty.setText(R.string.empty_list_message)
    }

    companion object {
        fun newInstance(bundle: Bundle): FavouritesFragment {
            val fragment = FavouritesFragment()
            fragment.arguments = bundle
            return fragment
        }
    }
}