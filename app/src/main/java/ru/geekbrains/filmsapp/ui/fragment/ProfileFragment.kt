package ru.geekbrains.filmsapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import ru.geekbrains.filmsapp.R
import ru.geekbrains.filmsapp.databinding.FragmentMovieBinding
import ru.geekbrains.filmsapp.databinding.FragmentProfileBinding
import ru.geekbrains.filmsapp.model.data.Account
import ru.geekbrains.filmsapp.model.data.Trend
import ru.geekbrains.filmsapp.viewmodel.viewstate.ProfileViewState
import ru.geekbrains.filmsapp.viewmodel.vm.ProfileViewModel

class ProfileFragment : BaseFragment<Account?, ProfileViewState, FragmentProfileBinding>() {

    override val viewModel: ProfileViewModel by lazy { ViewModelProvider(this).get(ProfileViewModel::class.java) }
    override val layoutRes: Int = R.layout.fragment_profile
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentProfileBinding
            = { layoutInflater: LayoutInflater, viewGroup: ViewGroup?, b: Boolean -> FragmentProfileBinding.inflate(layoutInflater)}


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.getAccountFromRemote()
    }

    override fun bindView(view: View) {

    }

    private fun setData(trend: Trend) {

    }

    companion object {
        fun newInstance(bundle: Bundle): ProfileFragment {
            val fragment = ProfileFragment()
            fragment.arguments = bundle
            return fragment
        }
    }
}
