package com.mina.mvvm.base.data.auth.data_source.remote

import com.mina.mvvm.base.data.remote.BaseRemoteDataSource
import com.mina.mvvm.base.domain.auth.entity.request.LogInRequest
import javax.inject.Inject

class AuthRemoteDataSource @Inject constructor(
  private val apiService: AuthServices
) : BaseRemoteDataSource() {

  suspend fun logIn(request: LogInRequest) = safeApiCall {
    apiService.logIn(request)
  }
}