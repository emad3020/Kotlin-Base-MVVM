package com.mina.mvvm.base.domain.account.useCase

import com.mina.mvvm.base.domain.account.repository.AccountRepository
import javax.inject.Inject

class CheckFirstTimeUseCase @Inject constructor(
  private val accountRepository: AccountRepository
) {

  operator fun invoke() = accountRepository.isFirstTime()
}