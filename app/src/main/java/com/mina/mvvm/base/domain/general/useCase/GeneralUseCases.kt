package com.mina.mvvm.base.domain.general.useCase

import com.mina.mvvm.base.domain.account.useCase.CheckFirstTimeUseCase
import com.mina.mvvm.base.domain.account.useCase.CheckLoggedInUserUseCase
import com.mina.mvvm.base.domain.account.useCase.SetFirstTimeUseCase

class GeneralUseCases(
  val checkFirstTimeUseCase: CheckFirstTimeUseCase,
  val checkLoggedInUserUseCase: CheckLoggedInUserUseCase,
  val setFirstTimeUseCase: SetFirstTimeUseCase,
  val clearPreferencesUseCase: ClearPreferencesUseCase
)