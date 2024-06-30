package com.mina.mvvm.base.domain.account.useCase

import com.core.presentation.useCases.FlowUseCase
import com.core.utils.BaseResponse
import com.core.utils.annotations.IoDispatcher
import com.mina.mvvm.base.domain.account.repository.AccountRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LogOutUseCase @Inject constructor(
  @IoDispatcher dispatcher: CoroutineDispatcher,
  private val accountRepository: AccountRepository
) : FlowUseCase<Unit,BaseResponse<Boolean>>(dispatcher) {

  override fun execute(parameters: Unit): Flow<BaseResponse<Boolean>>  = flow {

    emit(
      accountRepository.logOut()
    )
  }

//  operator fun invoke(): Flow<Resource<BaseResponse<Boolean>>> = flow {
//    emit(Resource.Loading)
//
//    val result = accountRepository.logOut()
//    if (result is Resource.Success && result.value.result) {
//      accountRepository.clearPreferences()
//    }
//
//    emit(result)
//  }.flowOn(Dispatchers.IO)
}