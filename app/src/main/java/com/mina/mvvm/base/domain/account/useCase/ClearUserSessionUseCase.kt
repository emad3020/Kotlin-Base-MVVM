package com.mina.mvvm.base.domain.account.useCase

import com.core.presentation.useCases.UseCase
import com.core.utils.annotations.IoDispatcher
import com.mina.mvvm.base.domain.account.repository.AccountRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class ClearUserSessionUseCase @Inject constructor(
  @IoDispatcher dispatcher: CoroutineDispatcher,
  private val accountRepository: AccountRepository
) : UseCase<Unit, Unit>(dispatcher) {

  override suspend fun execute(parameters: Unit) {
    accountRepository.clearPreferences()
  }
}