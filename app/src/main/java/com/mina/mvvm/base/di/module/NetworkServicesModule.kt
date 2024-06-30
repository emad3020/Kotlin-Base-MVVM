package com.mina.mvvm.base.di.module

import com.mina.mvvm.base.data.account.data_source.remote.AccountServices
import com.mina.mvvm.base.data.auth.data_source.remote.AuthServices
import com.mina.mvvm.base.data.general.data_source.remote.GeneralServices
import com.mina.mvvm.base.data.home.data_source.remote.HomeServices
import com.mina.mvvm.base.data.search.data_source.remote.SearchServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkServicesModule {

  @Provides
  @Singleton
  fun provideAuthServices(retrofit: Retrofit): AuthServices =
    retrofit.create(AuthServices::class.java)

  @Provides
  @Singleton
  fun provideAccountServices(
    retrofit: Retrofit): AccountServices =
    retrofit.create(AccountServices::class.java)

  @Provides
  @Singleton
  fun provideGeneralServices(retrofit: Retrofit): GeneralServices =
    retrofit.create(GeneralServices::class.java)

  @Provides
  @Singleton
  fun provideSearchServices(retrofit: Retrofit): SearchServices =
    retrofit.create(SearchServices::class.java)

  @Provides
  @Singleton
  fun provideHomeServices(retrofit: Retrofit): HomeServices =
    retrofit.create(HomeServices::class.java)
}