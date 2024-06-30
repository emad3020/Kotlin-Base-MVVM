package com.mina.mvvm.base.presentation.auth.logIn

import androidx.lifecycle.viewModelScope
import com.core.presentation.base.BaseViewModel
import com.core.presentation.base.utils.SingleLiveEvent
import com.core.utils.BaseResponse
import com.core.utils.Resource
import com.mina.mvvm.base.domain.auth.entity.model.User
import com.mina.mvvm.base.domain.auth.entity.request.LogInRequest
import com.mina.mvvm.base.domain.auth.useCase.LogInUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class LogInViewModel @Inject constructor(
  private val logInUseCase: LogInUseCase
) : BaseViewModel() {

  var request = LogInRequest()
  private val _logInResponse = MutableStateFlow<Resource<BaseResponse<User>>>(Resource.Empty)
  val logInResponse = _logInResponse

  var togglePassword = SingleLiveEvent<Void>()
  var openForgotPassword = SingleLiveEvent<Void>()

  var validationException = SingleLiveEvent<Int>()

  fun onPasswordToggleClicked() {
    togglePassword.call()
  }

  fun onForgotPasswordClicked() {
    openForgotPassword.call()
  }

  fun onLogInClicked() {
    logInUseCase(request)
      .catch { exception -> validationException.value = exception.message?.toInt() }
      .onEach { result ->
        _logInResponse.value = result
      }
      .launchIn(viewModelScope)
  }
}