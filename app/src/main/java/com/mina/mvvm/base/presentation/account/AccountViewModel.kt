package com.mina.mvvm.base.presentation.account

import androidx.lifecycle.viewModelScope
import com.core.presentation.base.BaseViewModel
import com.core.presentation.base.utils.SingleLiveEvent
import com.core.utils.BaseResponse
import com.core.utils.Resource
import com.mina.mvvm.base.domain.account.useCase.AccountUseCases
import com.mina.mvvm.base.domain.account.useCase.ClearUserSessionUseCase
import com.mina.mvvm.base.domain.account.useCase.LogOutUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(
  private val logOutUseCase : LogOutUseCase,
  private val clearUserSessionUseCase: ClearUserSessionUseCase
) : BaseViewModel() {

  private val _logOutState = MutableSharedFlow<Resource<BaseResponse<Boolean>>>()
  private val _sessionClearState = MutableSharedFlow<Resource<Unit>>()

  val logOutState : SharedFlow<Resource<BaseResponse<Boolean>>>
    get() = _logOutState
  val sessionClearState : SharedFlow<Resource<Unit>>
    get() = _sessionClearState

  fun logOut() {
    logOutUseCase(Unit)
      .onEach { result ->
        _logOutState.emit(result)
      }
      .launchIn(viewModelScope)
  }


  fun clearUserSession() {
    viewModelScope.launch {
      _sessionClearState.emit(
        clearUserSessionUseCase(Unit)
      )
    }
  }
}