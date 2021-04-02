package ru.geekbrains.filmsapp.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import ru.geekbrains.filmsapp.R
import ru.geekbrains.filmsapp.databinding.FragmentFavouritesBinding
import ru.geekbrains.filmsapp.model.data.Account
import ru.geekbrains.filmsapp.model.data.Movie
import ru.geekbrains.filmsapp.ui.adapter.MovieAdapter
import ru.geekbrains.filmsapp.ui.extension.createCancelableAlertDialog
import ru.geekbrains.filmsapp.ui.extension.makeLongSnackbar
import ru.geekbrains.filmsapp.viewmodel.viewstate.FavouriteViewState
import ru.geekbrains.filmsapp.viewmodel.vm.FavouritesViewModel


class FavouritesFragment : BaseFragment<List<Movie>?, FavouriteViewState, FragmentFavouritesBinding>() {

    override lateinit var viewModel: FavouritesViewModel
    override val layoutRes: Int = R.layout.fragment_favourites
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentFavouritesBinding
            = { layoutInflater: LayoutInflater, viewGroup: ViewGroup?, b: Boolean -> FragmentFavouritesBinding.inflate(
        layoutInflater
    )}

    private lateinit var bundle: Account
    private lateinit var movieAdapter: MovieAdapter
    private var triedFromLocal: Boolean = false  // used to indicate, whether the request to db has been already made

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FavouritesViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bundle = arguments?.getParcelable(BUNDLE_EXTRA) ?: Account()
        viewModel.getLiveData().observe(viewLifecycleOwner, Observer { state ->
            state.apply {
                if (data == null && error == null) {
                    renderLoading()
                } else {
                    data?.let { renderData(it) }
                    error?.let { renderError(it) }
                }
            }
        })

        triedFromLocal = false  // reset flag
        viewModel.getFavouritesFromRemote(bundle.id.toString())
    }

    override fun bindView(view: View) {
        binding.recycleView.layoutManager = GridLayoutManager(context, 3)
        movieAdapter = MovieAdapter(view.context, {})
        binding.recycleView.adapter = movieAdapter

        view.context.createCancelableAlertDialog(R.string.bottom_nav_item_favourites)
    }

    override fun renderLoading() {
        binding.fragmentLoading.layoutProgressbar.visibility = View.VISIBLE
        binding.recycleView.visibility = View.GONE
        binding.fragmentEmpty.fragmentEmpty.visibility = View.GONE
    }

    override fun renderData(data: List<Movie>?) {
        binding.fragmentLoading.layoutProgressbar.visibility = View.GONE
        binding.recycleView.visibility = View.VISIBLE
        binding.fragmentEmpty.fragmentEmpty.visibility = View.GONE

        data?.let {
            binding.fragmentEmpty.fragmentEmpty.visibility = View.GONE
            movieAdapter.values = data
        } ?: showEmptyView()

    }

    override fun renderError(error: Throwable) {
        if (!triedFromLocal) {
            // make request to local db 1 time and after
            triedFromLocal = true
            view?.makeLongSnackbar(error.message ?: getString(R.string.error_message_with_reload))
            viewModel.getFavouritesFromLocal()
            return
        }

        error.message?.let { showEmptyView(it) } ?: showEmptyView()
    }

    private fun showEmptyView(msg: String = getString(R.string.empty_list_message)) {
        binding.fragmentLoading.layoutProgressbar.visibility = View.GONE
        binding.recycleView.visibility = View.GONE
        binding.fragmentEmpty.fragmentEmpty.visibility = View.VISIBLE

        binding.fragmentEmpty.textEmpty.text = msg
    }

    companion object {

        const val BUNDLE_EXTRA = "favourites"

        fun newInstance(bundle: Bundle): FavouritesFragment {
            Log.d("FavouritesFragment", "newInstance")
            val fragment = FavouritesFragment()
            fragment.arguments = bundle
            return fragment
        }
    }
}