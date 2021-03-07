package ru.geekbrains.filmsapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import ru.geekbrains.filmsapp.R
import ru.geekbrains.filmsapp.databinding.FragmentMoviesBinding
import ru.geekbrains.filmsapp.model.data.Movie
import ru.geekbrains.filmsapp.model.data.Trend
import ru.geekbrains.filmsapp.viewmodel.viewstate.MovieViewState
import ru.geekbrains.filmsapp.viewmodel.vm.MoviesViewModel

class MoviesFragment : BaseFragment<List<Movie>?, MovieViewState, FragmentMoviesBinding>() {

    override val viewModel: MoviesViewModel by lazy { ViewModelProvider(this).get(MoviesViewModel::class.java) }
    override val layoutRes: Int = R.layout.fragment_movies
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentMoviesBinding
        get() = TODO("Not yet implemented")


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.getGenresFromRemote()
        viewModel.getMoviesByGenreFromRemote(listOf(0))  // TODO: выбор жанра и передача id туда
    }

    override fun bindView(view: View) {

    }

    private fun setData(trend: Trend) {

    }

    companion object {
        fun newInstance() = MoviesFragment()
    }
}