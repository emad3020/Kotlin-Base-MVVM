package com.mina.mvvm.base.presentation.intro.intro

import android.widget.Toast
import androidx.fragment.app.viewModels
import com.core.presentation.R
import com.core.presentation.base.BaseFragment
import com.core.presentation.extensions.collect
import com.core.presentation.extensions.openActivityAndClearStack
import com.core.utils.Resource
import com.mina.base.mvvm.databinding.FragmentIntroBinding
import com.mina.mvvm.base.presentation.auth.AuthActivity
import com.mina.mvvm.base.presentation.home.HomeActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class IntroFragment : BaseFragment<FragmentIntroBinding, IntroViewModel>(
  FragmentIntroBinding::inflate
) {

  override val viewModel: IntroViewModel by viewModels()

  override fun FragmentIntroBinding.initializeUI() {
    binding.btnGuestMode.text = resourceProvider.getSpannableString(
      spannableString = getString(R.string.continue_as_guest),
      isUnderlined = true,
      isBold = true,
      beforeTextResId = R.string.explore_app,
      spanColorRes = R.color.colorAccent
    )
  }

  override fun setupObservers() {
    collect(viewModel.isFirstTimeWithLogin) {

      when (it) {
        is Resource.Failure -> {
          binding.btnLogIn.hideLoading()
        }

        is Resource.Success -> {
          openActivityAndClearStack(AuthActivity::class.java)
        }

        else -> binding.btnLogIn.showLoading()
      }
    }

    collect(viewModel.isFirstTimeWithGuest) {
      when (it) {
        is Resource.Failure -> {
          binding.btnGuestMode.hideLoading()
        }

        is Resource.Success -> {
          openActivityAndClearStack(HomeActivity::class.java)
        }

        else -> binding.btnGuestMode.showLoading()
      }
    }


  }

  override fun registerListeners() {
    binding.apply {
      btnLogIn.setOnClickListener {
        Toast.makeText(requireContext(), "Test", Toast.LENGTH_SHORT).show()
        viewModel.setFirstTimeLogin(false)
      }

      btnGuestMode.setOnClickListener {
        viewModel.startGuestMode()
      }
    }
  }
}