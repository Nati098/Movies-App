package ru.geekbrains.filmsapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import ru.geekbrains.filmsapp.R
import ru.geekbrains.filmsapp.databinding.FragmentHomeBinding
import ru.geekbrains.filmsapp.model.data.Movie
import ru.geekbrains.filmsapp.model.data.Trend
import ru.geekbrains.filmsapp.ui.adapter.MovieAdapter
import ru.geekbrains.filmsapp.ui.extension.createCancelableAlertDialog
import ru.geekbrains.filmsapp.viewmodel.viewstate.HomeViewState
import ru.geekbrains.filmsapp.viewmodel.vm.HomeViewModel

class HomeFragment : BaseFragment<Trend?, HomeViewState, FragmentHomeBinding>() {

    override val viewModel: HomeViewModel by lazy { ViewModelProvider(this).get(HomeViewModel::class.java) }
    override val layoutRes: Int = R.layout.fragment_home
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentHomeBinding
            = { layoutInflater: LayoutInflater, viewGroup: ViewGroup?, b: Boolean -> FragmentHomeBinding.inflate(layoutInflater)}

    lateinit var trendAdapter: MovieAdapter
    lateinit var ratedAdapter: MovieAdapter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.getTrendingFromRemote()
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

        fun newInstance(bundle: Bundle): HomeFragment {
            val fragment = HomeFragment()
            fragment.arguments = bundle
            return fragment
        }
    }
}