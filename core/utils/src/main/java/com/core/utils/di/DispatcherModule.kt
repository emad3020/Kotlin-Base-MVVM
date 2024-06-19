package com.core.utils.di

import com.core.utils.annotations.DefaultDispatcher
import com.core.utils.annotations.IoDispatcher
import com.core.utils.annotations.MainDispatcher
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@InstallIn(SingletonComponent::class)
@Module
object DispatcherModule {

  @Provides
  @DefaultDispatcher
  fun providesDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default

  @Provides
  @IoDispatcher
  fun providesIoDispatcher(): CoroutineDispatcher = Dispatchers.IO

  @Provides
  @MainDispatcher
  fun providesMainDispatcher(): CoroutineDispatcher = Dispatchers.Main
}