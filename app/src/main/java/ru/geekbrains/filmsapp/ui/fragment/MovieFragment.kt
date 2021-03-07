package ru.geekbrains.filmsapp.ui.fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import ru.geekbrains.filmsapp.R
import ru.geekbrains.filmsapp.databinding.FragmentHomeBinding
import ru.geekbrains.filmsapp.model.data.Movie
import ru.geekbrains.filmsapp.viewmodel.viewstate.MovieViewState
import ru.geekbrains.filmsapp.viewmodel.vm.MovieViewModel

class MovieFragment : BaseFragment<Movie?, MovieViewState, FragmentHomeBinding>() {
    override val viewModel: MovieViewModel by lazy { ViewModelProvider(this).get(MovieViewModel::class.java) }
    override val layoutRes: Int = R.layout.fragment_movie
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentHomeBinding
        get() = TODO("Not yet implemented")

    override fun bindView(view: View) {
        TODO("Not yet implemented")
    }
}

//getMovieDetailsFromRemote