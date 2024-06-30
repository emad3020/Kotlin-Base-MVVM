package com.mina.mvvm.base.presentation.splash

import com.core.presentation.base.BaseViewModel
import com.mina.mvvm.base.domain.general.useCase.GeneralUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
  private val generalUseCases: GeneralUseCases
) : BaseViewModel() {

  fun isFirstTime() = generalUseCases.checkFirstTimeUseCase()
}