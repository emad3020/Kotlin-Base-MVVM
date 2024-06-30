package com.mina.mvvm.base.domain.account.useCase

import com.core.presentation.useCases.FlowUseCase
import com.core.utils.BaseResponse
import com.core.utils.annotations.IoDispatcher
import com.mina.mvvm.base.domain.account.entity.request.SendFirebaseTokenRequest
import com.mina.mvvm.base.domain.account.repository.AccountRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SendFirebaseTokenUseCase @Inject constructor(
  @IoDispatcher
  private val dispatcher: CoroutineDispatcher,
  private val accountRepository: AccountRepository
):FlowUseCase<SendFirebaseTokenRequest,BaseResponse<Boolean>>(dispatcher) {

  override fun execute(parameters: SendFirebaseTokenRequest): Flow<BaseResponse<Boolean>>  = flow {
    emit(
      accountRepository.sendFirebaseToken(parameters)
    )
  }
}