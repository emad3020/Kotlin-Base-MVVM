package com.mina.mvvm.base.domain.account.useCase

import com.core.presentation.useCases.FlowUseCase
import com.core.utils.annotations.IoDispatcher
import com.mina.mvvm.base.domain.account.repository.AccountRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SetFirstTimeUseCase @Inject constructor(
  @IoDispatcher
  private val dispatcher: CoroutineDispatcher,
  private val accountRepository: AccountRepository
): FlowUseCase<Boolean,Unit>(dispatcher) {

  override fun execute(parameters: Boolean): Flow<Unit> = flow {
    delay(13000)
    emit(
      accountRepository.setFirstTime(parameters)
    )
  }
}