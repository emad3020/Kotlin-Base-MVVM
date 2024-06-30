package com.mina.mvvm.base.domain.general.useCase

import com.mina.mvvm.base.domain.account.repository.AccountRepository
import javax.inject.Inject

class ClearPreferencesUseCase @Inject constructor(
  private val accountRepository: AccountRepository
) {

  operator fun invoke() = accountRepository.clearPreferences()
}