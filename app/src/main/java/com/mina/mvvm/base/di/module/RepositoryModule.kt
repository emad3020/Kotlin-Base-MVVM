package com.mina.mvvm.base.di.module

import com.mina.mvvm.base.data.account.data_source.remote.AccountRemoteDataSource
import com.mina.mvvm.base.data.account.repository.AccountRepositoryImpl
import com.mina.mvvm.base.data.auth.data_source.remote.AuthRemoteDataSource
import com.mina.mvvm.base.data.auth.repository.AuthRepositoryImpl
import com.mina.mvvm.base.data.general.data_source.remote.GeneralRemoteDataSource
import com.mina.mvvm.base.data.general.repository.GeneralRepositoryImpl
import com.mina.mvvm.base.data.home.data_source.remote.HomeRemoteDataSource
import com.mina.mvvm.base.data.home.repository.HomeRepositoryImpl
import com.mina.mvvm.base.data.local.preferences.AppPreferences
import com.mina.mvvm.base.data.search.data_source.remote.SearchRemoteDataSource
import com.mina.mvvm.base.data.search.repository.SearchRepositoryImpl
import com.mina.mvvm.base.domain.account.repository.AccountRepository
import com.mina.mvvm.base.domain.auth.repository.AuthRepository
import com.mina.mvvm.base.domain.general.repository.GeneralRepository
import com.mina.mvvm.base.domain.repository.HomeRepository
import com.mina.mvvm.base.domain.repository.SearchRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

  @Provides
  @Singleton
  fun provideGeneralRepository(
    remoteDataSource: GeneralRemoteDataSource,
    appPreferences: AppPreferences
  ): GeneralRepository = GeneralRepositoryImpl(remoteDataSource, appPreferences)

  @Provides
  @Singleton
  fun provideAuthRepository(
    remoteDataSource: AuthRemoteDataSource
  ): AuthRepository = AuthRepositoryImpl(remoteDataSource)

  @Provides
  @Singleton
  fun provideSearchRepository(
    remoteDataSource: SearchRemoteDataSource
  ): SearchRepository = SearchRepositoryImpl(remoteDataSource)

  @Provides
  @Singleton
  fun provideHomeRepository(
    remoteDataSource: HomeRemoteDataSource
  ): HomeRepository = HomeRepositoryImpl(remoteDataSource)
}

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModules {
  @Binds
  @Singleton
  fun provideAccountRepository( accountRepositoryImpl: AccountRepositoryImpl): AccountRepository
}