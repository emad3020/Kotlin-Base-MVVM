package com.mina.mvvm.base.presentation.intro.intro

import androidx.lifecycle.viewModelScope
import com.core.presentation.base.BaseViewModel
import com.core.utils.Resource
import com.mina.mvvm.base.domain.account.useCase.SetFirstTimeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class IntroViewModel @Inject constructor(
  private val firstTimeUseCase: SetFirstTimeUseCase
) : BaseViewModel() {
  private val _isFirstTimeWithLogin = MutableSharedFlow<Resource<Unit>>()
  private val _isFirstTimeWithGuest = MutableSharedFlow<Resource<Unit>>()

  val isFirstTimeWithLogin : SharedFlow<Resource<Unit>>
    get() = _isFirstTimeWithLogin

  val isFirstTimeWithGuest : SharedFlow<Resource<Unit>>
    get() = _isFirstTimeWithGuest


  fun setFirstTimeLogin(isFirstTime: Boolean) {
    firstTimeUseCase(isFirstTime)
      .onEach {
        _isFirstTimeWithLogin.emit(it)
      }.launchIn(viewModelScope)
  }

  fun startGuestMode() {
    firstTimeUseCase(false)
      .onEach {
        _isFirstTimeWithGuest.emit(it)
      }.launchIn(viewModelScope)
  }
}