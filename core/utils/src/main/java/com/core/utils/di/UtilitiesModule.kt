package com.core.utils.di

import com.core.utils.connection.ConnectivityManager
import com.core.utils.connection.ConnectivityManagerImpl
import com.core.utils.providers.ResourceProvider
import com.core.utils.providers.ResourceProviderImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
interface UtilitiesModule {

  @Binds
  fun bindResourceProvider(
    provider: ResourceProviderImpl
  ): ResourceProvider

  @Binds
  fun bindConnectivityManager(
    connectivityManagerImpl: ConnectivityManagerImpl
  ): ConnectivityManager
}