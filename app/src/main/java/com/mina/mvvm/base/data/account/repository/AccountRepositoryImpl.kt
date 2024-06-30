package com.mina.mvvm.base.data.account.repository

import com.mina.mvvm.base.data.account.data_source.remote.AccountRemoteDataSource
import com.mina.mvvm.base.data.account.data_source.remote.AccountServices
import com.mina.mvvm.base.data.local.preferences.AppPreferences
import com.mina.mvvm.base.domain.account.entity.request.SendFirebaseTokenRequest
import com.mina.mvvm.base.domain.account.repository.AccountRepository
import com.mina.mvvm.base.domain.auth.entity.model.User
import javax.inject.Inject

class AccountRepositoryImpl @Inject constructor(
  private val apiService: AccountServices,
  private val appPreferences: AppPreferences
) : AccountRepository {

  override suspend fun sendFirebaseToken(request: SendFirebaseTokenRequest) =
    apiService.sendFirebaseToken(request)

  override suspend fun logOut() = apiService.logOut()

  override fun isFirstTime() = appPreferences.isFirstTime

  override fun isLoggedIn() = appPreferences.isLoggedIn

  override fun saveFirebaseTokenToLocal(firebaseToken: String) {
    appPreferences.firebaseToken = firebaseToken
  }

  override fun getFirebaseToken() = appPreferences.firebaseToken

  override fun saveUserToLocal(user: User) {
    appPreferences.userData = user
  }

  override fun setFirstTime(isFirstTime: Boolean) {
    appPreferences.isFirstTime = isFirstTime
  }

  override fun clearPreferences() = appPreferences.clearPreferences()
}