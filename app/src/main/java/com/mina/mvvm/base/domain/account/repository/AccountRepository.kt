package com.mina.mvvm.base.domain.account.repository

import com.core.utils.BaseResponse
import com.core.utils.Resource
import com.mina.mvvm.base.domain.account.entity.request.SendFirebaseTokenRequest
import com.mina.mvvm.base.domain.auth.entity.model.User

interface AccountRepository {

  suspend fun sendFirebaseToken(request: SendFirebaseTokenRequest): BaseResponse<Boolean>

  suspend fun logOut(): BaseResponse<Boolean>

  fun isFirstTime(): Boolean

  fun isLoggedIn(): Boolean

  fun saveFirebaseTokenToLocal(firebaseToken: String)

  fun getFirebaseToken(): String?

  fun saveUserToLocal(user: User)

  fun setFirstTime(isFirstTime: Boolean)

  fun clearPreferences()
}