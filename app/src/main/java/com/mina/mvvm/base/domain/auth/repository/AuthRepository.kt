package com.mina.mvvm.base.domain.auth.repository

import com.core.utils.BaseResponse
import com.core.utils.Resource
import com.mina.mvvm.base.domain.auth.entity.model.User
import com.mina.mvvm.base.domain.auth.entity.request.LogInRequest

interface AuthRepository {

  suspend fun logIn(request: LogInRequest): Resource<BaseResponse<User>>
}