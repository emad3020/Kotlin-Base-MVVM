package com.mina.mvvm.base.presentation.auth.forgotPassword

import androidx.fragment.app.viewModels
import com.core.presentation.base.BaseFragment
import com.core.presentation.extensions.backToPreviousScreen
import com.mina.base.mvvm.databinding.FragmentForgotPasswordBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ForgotPasswordFragment : BaseFragment<FragmentForgotPasswordBinding, ForgotPasswordViewModel>(
  FragmentForgotPasswordBinding::inflate
) {

  override val viewModel: ForgotPasswordViewModel by viewModels()

  override fun setupObservers() {
    viewModel.backToPreviousScreen.observe(this) { backToPreviousScreen() }
  }
}