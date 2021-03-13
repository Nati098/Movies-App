package ru.geekbrains.filmsapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_movie.*
import ru.geekbrains.filmsapp.R
import ru.geekbrains.filmsapp.databinding.FragmentMovieBinding
import ru.geekbrains.filmsapp.model.data.Movie
import ru.geekbrains.filmsapp.ui.adapter.DATE_TIME_FORMAT
import ru.geekbrains.filmsapp.ui.adapter.MovieInfoAdapter
import ru.geekbrains.filmsapp.viewmodel.viewstate.MovieViewState
import ru.geekbrains.filmsapp.viewmodel.vm.MovieViewModel
import java.text.SimpleDateFormat
import java.util.*

class MovieFragment : BaseFragment<Movie?, MovieViewState, FragmentMovieBinding>() {
    override val viewModel: MovieViewModel by lazy { ViewModelProvider(this).get(MovieViewModel::class.java) }
    override val layoutRes: Int = R.layout.fragment_movie
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentMovieBinding
            = { layoutInflater: LayoutInflater, viewGroup: ViewGroup?, b: Boolean -> FragmentMovieBinding.inflate(layoutInflater)}

    override fun bindView(view: View) {
        arguments?.getParcelable<Movie>(BUNDLE_EXTRA)?.let {
//        image_view_poster
            recycler_movie_details.adapter = MovieInfoAdapter(
                listOf(
                    view.resources.getString(R.string.genres_template, ""),
                    view.resources.getString(R.string.release_date_template, SimpleDateFormat(DATE_TIME_FORMAT, Locale.getDefault()).format(it.releaseDate)),
                    view.resources.getString(R.string.popularity_template, it.popularity)
                ))
            text_view_overview.text = it.overview
        } ?: showError()
    }

    private fun showError() {
        // TODO
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