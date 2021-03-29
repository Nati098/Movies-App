package ru.geekbrains.filmsapp.ui.fragment

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.bumptech.glide.Glide
import ru.geekbrains.filmsapp.R
import ru.geekbrains.filmsapp.databinding.FragmentMovieBinding
import ru.geekbrains.filmsapp.model.data.Movie
import ru.geekbrains.filmsapp.ui.adapter.MovieInfoAdapter
import ru.geekbrains.filmsapp.ui.extension.format
import ru.geekbrains.filmsapp.viewmodel.viewstate.MovieViewState
import ru.geekbrains.filmsapp.viewmodel.vm.MovieViewModel

const val INTENT_FILTER = "movie_intent_filter"
const val MOVIE_DETAILS_EXTRA = "movie_details"
const val LOAD_RESULT_EXTRA = "load_result"
const val ON_SUCCESS_EXTRA = "on_success"
const val EMPTY_RESPONSE_EXTRA = "empty_reponse"
const val EMPTY_INTENT_EXTRA = "empty_intent"
const val EMPTY_DATA_EXTRA = "empty_data"
const val MALFORMED_URL_EXTRA = "malformed_url"
const val ERROR_REQUEST_EXTRA = "error_request"
const val ERROR_REQUEST_MSG_EXTRA = "error_request_msg"

class MovieFragment : BaseFragment<Movie?, MovieViewState, FragmentMovieBinding>() {
    override val viewModel: MovieViewModel by lazy { ViewModelProvider(this).get(MovieViewModel::class.java) }
    override val layoutRes: Int = R.layout.fragment_movie
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentMovieBinding
            = { layoutInflater: LayoutInflater, viewGroup: ViewGroup?, b: Boolean -> FragmentMovieBinding.inflate(layoutInflater)}

    private val loadDataReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            when (intent.getStringExtra(LOAD_RESULT_EXTRA)) {
                EMPTY_INTENT_EXTRA -> renderError(Throwable("Error while loading movie details: Intent is empty"))
                EMPTY_DATA_EXTRA -> renderError(Throwable("Error while loading movie details: Data is empty"))
                EMPTY_RESPONSE_EXTRA -> renderError(Throwable("Error while loading movie details: Response is empty"))
                ERROR_REQUEST_EXTRA -> renderError(Throwable("Error while loading movie details: Request with error"))
                ERROR_REQUEST_MSG_EXTRA -> renderError(Throwable(intent.getStringExtra(ERROR_REQUEST_MSG_EXTRA)))
                MALFORMED_URL_EXTRA -> renderError(Throwable("Error while loading movie details: Request with error"))
                ON_SUCCESS_EXTRA -> renderData(intent.getParcelableExtra(MOVIE_DETAILS_EXTRA))
                else -> renderError(Throwable("Error while loading movie details: unknown error"))
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context?.let {
            LocalBroadcastManager.getInstance(it)
                .registerReceiver(loadDataReceiver, IntentFilter(INTENT_FILTER))
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getLiveData().observe(viewLifecycleOwner, Observer { state ->
            state.apply {
                if (movie == null && error == null) {
                    renderLoading()
                }
                else {
                    movie?.let { renderData(it) }
                    error?.let { renderError(it) }
                }
            }
        })

        val movieBundle = arguments?.getParcelable<Movie>(BUNDLE_EXTRA)
        movieBundle?.let {
            viewModel.getMovieDetailsFromRemote(requireContext(), id)
        } ?: renderError(Throwable("There is no movie: movieBundle=null"))
    }

    override fun bindView(view: View) {}

    override fun renderLoading() {
        binding.fragmentLoading.layoutProgressbar.visibility = View.VISIBLE
        binding.layoutMovieInfo.visibility = View.GONE
        binding.fragmentEmpty.fragmentEmpty.visibility = View.GONE
    }

    override fun renderData(data: Movie?) {
        binding.fragmentLoading.layoutProgressbar.visibility = View.GONE
        binding.layoutMovieInfo.visibility = View.VISIBLE
        binding.fragmentEmpty.fragmentEmpty.visibility = View.GONE

        data?.let {
            it.posterPath?.apply {
                Glide.with(view).load(this).into(binding.imageViewPoster)
            }
            binding.recyclerMovieDetails.adapter = MovieInfoAdapter(
                listOf(
                    view?.resources?.getString(R.string.genres_template, "") ?: "",
                    view?.resources?.getString(R.string.release_date_template, it.releaseDate.format()) ?: "",
                    view?.resources?.getString(R.string.popularity_template, it.popularity) ?: ""
                ))
            binding.textViewOverview.text = it.overview
        } ?: renderError(Throwable("Cannot parcel movie from arguments with key=$BUNDLE_EXTRA"))
    }

    override fun renderError(error: Throwable) {
        binding.fragmentLoading.layoutProgressbar.visibility = View.GONE
        binding.layoutMovieInfo.visibility = View.GONE
        binding.fragmentEmpty.fragmentEmpty.visibility = View.VISIBLE

        error.message?.let {
            binding.fragmentEmpty.textEmpty.text = it
        } ?: binding.fragmentEmpty.textEmpty.setText(R.string.error_message)
    }

    override fun onDestroy() {
        context?.let {
            LocalBroadcastManager.getInstance(it).unregisterReceiver(loadDataReceiver)
        }
        super.onDestroy()
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
