package com.mina.mvvm.base.data.account.data_source.remote

import com.mina.mvvm.base.data.remote.BaseRemoteDataSource
import com.mina.mvvm.base.domain.account.entity.request.SendFirebaseTokenRequest
import javax.inject.Inject

class AccountRemoteDataSource @Inject constructor(private val apiService: AccountServices) :
  BaseRemoteDataSource() {

  suspend fun sendFirebaseToken(request: SendFirebaseTokenRequest) =
    apiService.sendFirebaseToken(request)

  suspend fun logOut() = apiService.logOut()
}