package com.mina.mvvm.base.presentation.account

import androidx.fragment.app.viewModels
import com.core.presentation.R
import com.core.presentation.base.BaseFragment
import com.core.presentation.extensions.getMyString
import com.core.presentation.extensions.handleApiError
import com.core.presentation.extensions.hide
import com.core.presentation.extensions.hideKeyboard
import com.core.presentation.extensions.openActivityAndClearStack
import com.core.presentation.extensions.collect
import com.core.presentation.extensions.showPrettyPopUp
import com.core.utils.Resource
import com.mina.base.mvvm.databinding.FragmentAccountBinding
import com.mina.mvvm.base.presentation.auth.AuthActivity
import com.mina.mvvm.base.presentation.intro.IntroActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AccountFragment : BaseFragment<FragmentAccountBinding, AccountViewModel>(
  FragmentAccountBinding::inflate
) {

  override val viewModel: AccountViewModel by viewModels()

  override fun FragmentAccountBinding.initializeUI() {
    includedToolbar.apply {
      toolbarTitle.text = resourceProvider.getString(R.string.account)
      backIv.hide()
    }
  }

  override fun registerListeners() {
    binding.apply {
      btnLogOut.setOnClickListener {
        showPrettyPopUp(
          title = getMyString(R.string.log_out),
          content = getMyString(R.string.log_out_hint),
          isCancelable = false,
          positiveButtonText = getMyString(R.string.log_out),
          positiveButtonClick = {
            viewModel.logOut()
          }
        )
      }

    }
  }

  override fun setupObservers() {
    collect(viewModel.logOutState) {
      when (it) {
        is Resource.Success -> {
          hideLoading()
         viewModel.clearUserSession()
        }
        is Resource.Failure -> {
          hideLoading()
          // for test case only
          viewModel.clearUserSession()
        }

        else -> {
          hideKeyboard()
          showLoading()
        }
      }
    }

    collect(viewModel.sessionClearState) {
      when (it) {
        is Resource.Success -> {
          hideLoading()
         requireActivity().openActivityAndClearStack(IntroActivity::class.java)
        }
        is Resource.Failure -> {
          hideLoading()
          handleApiError(it)
        }

        else -> {
          hideKeyboard()
          showLoading()
        }
      }
    }

  }
}