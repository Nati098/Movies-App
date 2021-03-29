package ru.geekbrains.filmsapp.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import ru.geekbrains.filmsapp.R
import ru.geekbrains.filmsapp.databinding.FragmentProfileBinding
import ru.geekbrains.filmsapp.model.data.Account
import ru.geekbrains.filmsapp.model.data.Trend
import ru.geekbrains.filmsapp.ui.extension.createCancelableAlertDialog
import ru.geekbrains.filmsapp.viewmodel.viewstate.ProfileViewState
import ru.geekbrains.filmsapp.viewmodel.vm.ProfileViewModel

class ProfileFragment : BaseFragment<Account?, ProfileViewState, FragmentProfileBinding>() {

    override lateinit var viewModel: ProfileViewModel
    override val layoutRes: Int = R.layout.fragment_profile
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentProfileBinding
            = { layoutInflater: LayoutInflater, viewGroup: ViewGroup?, b: Boolean -> FragmentProfileBinding.inflate(layoutInflater)}


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getLiveData().observe(viewLifecycleOwner, Observer { state ->
            state.apply {
                if (data == null && error == null) {
                    renderLoading()
                }
                else {
                    data?.let { renderData(it) }
                    error?.let { renderError(it) }
                }
            }
        })
        viewModel.getAccountFromRemote()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.getAccountFromRemote()
    }

    override fun bindView(view: View) {
        view.context.createCancelableAlertDialog(R.string.bottom_nav_item_profile)
    }

    override fun renderLoading() {
        binding.fragmentLoading.layoutProgressbar.visibility = View.VISIBLE
        binding.textNotifications.visibility = View.GONE
        binding.fragmentEmpty.fragmentEmpty.visibility = View.GONE
    }

    override fun renderData(data: Account?) {
        binding.fragmentLoading.layoutProgressbar.visibility = View.GONE
        binding.textNotifications.visibility = View.VISIBLE
        binding.fragmentEmpty.fragmentEmpty.visibility = View.GONE

        data?.let {
            binding.textNotifications.text = it.toString()
        } ?: renderError(Throwable("Unexpectable, but account data is empty"))
    }

    override fun renderError(error: Throwable) {
        binding.fragmentLoading.layoutProgressbar.visibility = View.GONE
        binding.textNotifications.visibility = View.GONE
        binding.fragmentEmpty.fragmentEmpty.visibility = View.VISIBLE

        error.message?.let {
            binding.fragmentEmpty.textEmpty.text = it
        } ?: binding.fragmentEmpty.textEmpty.setText(R.string.error_message)
    }

    companion object {
        const val BUNDLE_EXTRA = "account_data"

        fun newInstance(bundle: Bundle): ProfileFragment {
            Log.d("ProfileFragment", "newInstance")
            val fragment = ProfileFragment()
            fragment.arguments = bundle
            return fragment
        }
    }
}
