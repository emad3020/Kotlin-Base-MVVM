package com.mina.mvvm.base.di.module

import com.mina.mvvm.base.domain.account.repository.AccountRepository
import com.mina.mvvm.base.domain.account.useCase.AccountUseCases
import com.mina.mvvm.base.domain.account.useCase.CheckFirstTimeUseCase
import com.mina.mvvm.base.domain.account.useCase.CheckLoggedInUserUseCase
import com.mina.mvvm.base.domain.account.useCase.LogOutUseCase
import com.mina.mvvm.base.domain.account.useCase.SaveUserToLocalUseCase
import com.mina.mvvm.base.domain.account.useCase.SendFirebaseTokenUseCase
import com.mina.mvvm.base.domain.account.useCase.SetFirstTimeUseCase
import com.mina.mvvm.base.domain.auth.repository.AuthRepository
import com.mina.mvvm.base.domain.auth.useCase.LogInUseCase
import com.mina.mvvm.base.domain.general.useCase.ClearPreferencesUseCase
import com.mina.mvvm.base.domain.general.useCase.GeneralUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

  @Provides
  @Singleton
  fun provideLogInUseCase(
    authRepository: AuthRepository,
    saveUserToLocalUseCase: SaveUserToLocalUseCase
  ): LogInUseCase = LogInUseCase(authRepository, saveUserToLocalUseCase)

  @Provides
  @Singleton
  fun provideCheckFirstTimeUseCase(
    accountRepository: AccountRepository
  ): CheckFirstTimeUseCase = CheckFirstTimeUseCase(accountRepository)

  @Provides
  @Singleton
  fun provideCheckLoggedInUserUseCase(
    accountRepository: AccountRepository
  ): CheckLoggedInUserUseCase = CheckLoggedInUserUseCase(accountRepository)


  @Provides
  @Singleton
  fun provideGeneralUseCases(
    checkFirstTimeUseCase: CheckFirstTimeUseCase,
    checkLoggedInUserUseCase: CheckLoggedInUserUseCase,
    setFirstTimeUseCase: SetFirstTimeUseCase,
    clearPreferencesUseCase: ClearPreferencesUseCase
  ): GeneralUseCases =
    GeneralUseCases(checkFirstTimeUseCase, checkLoggedInUserUseCase, setFirstTimeUseCase, clearPreferencesUseCase)



  @Provides
  @Singleton
  fun provideClearPreferencesUseCase(
    accountRepository: AccountRepository
  ): ClearPreferencesUseCase = ClearPreferencesUseCase(accountRepository)

  @Provides
  @Singleton
  fun provideAccountUseCases(
    logOutUseCase: LogOutUseCase,
    sendFirebaseTokenUseCase: SendFirebaseTokenUseCase
  ): AccountUseCases = AccountUseCases(logOutUseCase, sendFirebaseTokenUseCase)
}