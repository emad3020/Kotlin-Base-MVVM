package com.mina.mvvm.base.domain.account.useCase

import com.core.presentation.useCases.FlowUseCase
import com.core.utils.annotations.IoDispatcher
import com.mina.mvvm.base.domain.account.repository.AccountRepository
import com.mina.mvvm.base.domain.auth.entity.model.User
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SaveUserToLocalUseCase @Inject constructor(
  @IoDispatcher
  private val dispatcher: CoroutineDispatcher,
  private val accountRepository: AccountRepository
): FlowUseCase<User,Unit> (dispatcher){

  override fun execute(parameters: User): Flow<Unit> = flow {
    emit(
      accountRepository.saveUserToLocal(parameters)
    )
  }
}