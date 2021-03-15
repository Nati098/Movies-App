package ru.geekbrains.filmsapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import ru.geekbrains.filmsapp.R
import ru.geekbrains.filmsapp.databinding.FragmentMovieBinding
import ru.geekbrains.filmsapp.model.data.Movie
import ru.geekbrains.filmsapp.ui.adapter.MovieInfoAdapter
import ru.geekbrains.filmsapp.ui.extension.format
import ru.geekbrains.filmsapp.ui.extension.makeLongSnackbar
import ru.geekbrains.filmsapp.viewmodel.viewstate.MovieViewState
import ru.geekbrains.filmsapp.viewmodel.vm.MovieViewModel

class MovieFragment : BaseFragment<Movie?, MovieViewState, FragmentMovieBinding>() {
    override val viewModel: MovieViewModel by lazy { ViewModelProvider(this).get(MovieViewModel::class.java) }
    override val layoutRes: Int = R.layout.fragment_movie
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentMovieBinding
            = { layoutInflater: LayoutInflater, viewGroup: ViewGroup?, b: Boolean -> FragmentMovieBinding.inflate(layoutInflater)}

    override fun bindView(view: View) {
        arguments?.getParcelable<Movie>(BUNDLE_EXTRA)?.let {
//        image_view_poster
            binding.recyclerMovieDetails.adapter = MovieInfoAdapter(
                listOf(
                    view.resources.getString(R.string.genres_template, ""),
                    view.resources.getString(R.string.release_date_template, it.releaseDate.format()),
                    view.resources.getString(R.string.popularity_template, it.popularity)
                ))
            binding.textViewOverview.text = it.overview
        } ?: showError()
    }

    private fun showError() {
        binding.textViewOverview.makeLongSnackbar(getString(R.string.error_message), getString(R.string.button_ok),null)
    }

    companion object {
        const val BUNDLE_EXTRA = "movie"

        fun newInstance(bundle: Bundle): MovieFragment {
            val fragment = MovieFragment()
            fragment.arguments = bundle
            return fragment
        }
    }
}

//getMovieDetailsFromRemote