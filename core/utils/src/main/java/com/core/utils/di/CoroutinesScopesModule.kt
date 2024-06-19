package com.core.utils.di

import com.core.utils.annotations.DefaultDispatcher
import com.core.utils.annotations.DefaultScope
import com.core.utils.annotations.IoScope
import com.core.utils.annotations.MainScope
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

@InstallIn(SingletonComponent::class)
@Module
object CoroutinesScopesModule {

  @DefaultScope
  @Singleton
  @Provides
  fun providesDefaultCoroutineScope(
    @DefaultDispatcher defaultDispatcher: CoroutineDispatcher
  ): CoroutineScope = CoroutineScope(SupervisorJob() + defaultDispatcher)

  @IoScope
  @Singleton
  @Provides
  fun providesIoCoroutineScope(
    @DefaultDispatcher defaultDispatcher: CoroutineDispatcher
  ): CoroutineScope = CoroutineScope(SupervisorJob() + defaultDispatcher)

  @MainScope
  @Singleton
  @Provides
  fun providesMainCoroutineScope(
    @DefaultDispatcher defaultDispatcher: CoroutineDispatcher
  ): CoroutineScope = CoroutineScope(SupervisorJob() + defaultDispatcher)
}