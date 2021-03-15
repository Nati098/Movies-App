package ru.geekbrains.filmsapp.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.getAccountFromRemote()
    }

    override fun bindView(view: View) {
        view.context.createCancelableAlertDialog(R.string.bottom_nav_item_profile)
    }

    private fun setData(trend: Trend) {

    }

    companion object {
        fun newInstance(bundle: Bundle): ProfileFragment {
            Log.d("ProfileFragment", "newInstance")
            val fragment = ProfileFragment()
            fragment.arguments = bundle
            return fragment
        }
    }
}
