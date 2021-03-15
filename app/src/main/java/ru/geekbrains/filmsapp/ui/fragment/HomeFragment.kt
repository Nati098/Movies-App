package ru.geekbrains.filmsapp.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import ru.geekbrains.filmsapp.R
import ru.geekbrains.filmsapp.databinding.FragmentHomeBinding
import ru.geekbrains.filmsapp.model.apiservice.WebApiService.LoaderListener
import ru.geekbrains.filmsapp.model.data.Movie
import ru.geekbrains.filmsapp.model.data.Trend
import ru.geekbrains.filmsapp.ui.adapter.MovieAdapter
import ru.geekbrains.filmsapp.ui.extension.createCancelableAlertDialog
import ru.geekbrains.filmsapp.viewmodel.viewstate.HomeViewState
import ru.geekbrains.filmsapp.viewmodel.vm.HomeViewModel

class HomeFragment : BaseFragment<Trend?, HomeViewState, FragmentHomeBinding>() {

    override lateinit var viewModel: HomeViewModel
    override val layoutRes: Int = R.layout.fragment_home
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentHomeBinding
            = { layoutInflater: LayoutInflater, viewGroup: ViewGroup?, b: Boolean -> FragmentHomeBinding.inflate(layoutInflater)}

    private lateinit var trendAdapter: MovieAdapter
    private lateinit var ratedAdapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.getTrendingFromRemote("movie", "week",
            object : LoaderListener<Trend> {
                override fun onLoaded(data: Trend) {
                    setTrendData(data)
                }

                override fun onFailed(throwable: Throwable) {
                    setTrendData(null)
                }
            })

        viewModel.getRatedFromRemote(
            object : LoaderListener<Trend> {
                override fun onLoaded(data: Trend) {
                    setRatedData(data.movies)
                }
                override fun onFailed(throwable: Throwable) {
                    setRatedData(null)
                }
            })
    }

    override fun bindView(view: View) {
        trendAdapter = MovieAdapter(view.context, {})
        binding.recycleViewTrend.adapter = trendAdapter

        ratedAdapter = MovieAdapter(view.context, {})
        binding.recycleViewRated.adapter = ratedAdapter

        view.context.createCancelableAlertDialog(R.string.bottom_nav_item_home)
    }

    private fun setTrendData(data: Trend?) {
        if (data == null) {
            binding.layoutTrend.visibility = View.GONE
            return
        }

        trendAdapter.values = data.movies
    }

    private fun setRatedData(data: List<Movie>?) {
        if (data == null) {
            binding.layoutRated.visibility = View.GONE
            return
        }

        ratedAdapter.values = data
    }

    private fun showMovie(movie: Movie) {
        activity?.supportFragmentManager?.apply {
            beginTransaction()
                .add(R.id.container, MovieFragment.newInstance(Bundle().apply {
                    putParcelable(MovieFragment.BUNDLE_EXTRA, movie)
                }))
                .addToBackStack("")
                .commitAllowingStateLoss()
        }
    }

    companion object {
        private val TAG = javaClass.canonicalName

        fun newInstance(bundle: Bundle): HomeFragment {
            Log.d(TAG, "newInstance")
            val fragment = HomeFragment()
            fragment.arguments = bundle
            return fragment
        }
    }
}