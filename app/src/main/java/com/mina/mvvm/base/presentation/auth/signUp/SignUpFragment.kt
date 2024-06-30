package com.mina.mvvm.base.presentation.auth.signUp

import androidx.fragment.app.viewModels
import com.core.presentation.base.BaseFragment
import com.core.presentation.extensions.backToPreviousScreen
import com.mina.base.mvvm.databinding.FragmentSignupBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpFragment : BaseFragment<FragmentSignupBinding, SignUpViewModel>(
  FragmentSignupBinding::inflate
) {

  override val viewModel: SignUpViewModel by viewModels()

  override fun setupObservers() {
    viewModel.backToPreviousScreen.observe(this) { backToPreviousScreen() }
  }
}