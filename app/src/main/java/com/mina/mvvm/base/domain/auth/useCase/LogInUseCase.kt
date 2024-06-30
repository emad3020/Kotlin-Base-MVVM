package com.mina.mvvm.base.domain.auth.useCase

import com.core.utils.BaseResponse
import com.core.utils.Resource
import com.mina.mvvm.base.domain.account.useCase.SaveUserToLocalUseCase
import com.mina.mvvm.base.domain.auth.entity.model.User
import com.mina.mvvm.base.domain.auth.entity.request.LogInRequest
import com.mina.mvvm.base.domain.auth.entity.request.LogInValidationException
import com.mina.mvvm.base.domain.auth.enums.AuthFieldsValidation
import com.mina.mvvm.base.domain.auth.repository.AuthRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class LogInUseCase @Inject constructor(
  private val authRepository: AuthRepository,
  private val saveUserToLocalUseCase: SaveUserToLocalUseCase
) {

  @Throws(LogInValidationException::class)
  operator fun invoke(request: LogInRequest): Flow<Resource<BaseResponse<User>>> = flow {
    if (request.email.isEmpty()) {
      throw LogInValidationException(AuthFieldsValidation.EMPTY_EMAIL.value.toString())
    }

//    if (!request.email.isValidEmail()) {
//      throw LogInValidationException(AuthFieldsValidation.INVALID_EMAIL.value.toString())
//    }

    if (request.password.isEmpty()) {
      throw LogInValidationException(AuthFieldsValidation.EMPTY_PASSWORD.value.toString())
    }

    emit(Resource.Loading)

    val result = authRepository.logIn(request)
    if (result is Resource.Success) {
      saveUserToLocalUseCase(result.value.result)
    }

    emit(result)
  }.flowOn(Dispatchers.IO)
}