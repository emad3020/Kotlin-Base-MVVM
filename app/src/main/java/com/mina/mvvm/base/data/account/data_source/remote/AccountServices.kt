package com.mina.mvvm.base.data.account.data_source.remote

import com.core.utils.BaseResponse
import com.mina.mvvm.base.domain.account.entity.request.SendFirebaseTokenRequest
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.PUT

interface AccountServices {

  @PUT("Account/FirebaseToken/Update")
  suspend fun sendFirebaseToken(@Body request: SendFirebaseTokenRequest): BaseResponse<Boolean>

  @DELETE("Account/Logout")
  suspend fun logOut(): BaseResponse<Boolean>
}