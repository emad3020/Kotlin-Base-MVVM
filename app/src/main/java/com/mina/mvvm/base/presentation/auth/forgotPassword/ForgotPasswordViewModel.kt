package com.mina.mvvm.base.presentation.auth.forgotPassword

import com.core.presentation.base.BaseViewModel
import com.core.presentation.base.utils.SingleLiveEvent
import com.mina.mvvm.base.domain.auth.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ForgotPasswordViewModel @Inject constructor(
  private val authRepository: AuthRepository
) : BaseViewModel() {

  val backToPreviousScreen = SingleLiveEvent<Void>()

  fun onBackClicked() {
    backToPreviousScreen.call()
  }
}