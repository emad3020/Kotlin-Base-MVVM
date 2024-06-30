package com.mina.mvvm.base.data.auth.repository

import com.mina.mvvm.base.data.auth.data_source.remote.AuthRemoteDataSource
import com.mina.mvvm.base.domain.auth.entity.request.LogInRequest
import com.mina.mvvm.base.domain.auth.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
  private val remoteDataSource: AuthRemoteDataSource
) : AuthRepository {

  override suspend fun logIn(request: LogInRequest) = remoteDataSource.logIn(request)
}