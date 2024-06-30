package com.mina.mvvm.base.data.auth.data_source.remote

import com.core.utils.BaseResponse
import com.mina.mvvm.base.domain.auth.entity.model.User
import com.mina.mvvm.base.domain.auth.entity.request.LogInRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthServices {

  @POST("Account/Login")
  suspend fun logIn(@Body request: LogInRequest): BaseResponse<User>
}