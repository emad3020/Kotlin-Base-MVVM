package com.core.config.di

import com.core.config.environment.EnvironmentConfig
import com.core.config.environment.EnvironmentConfigImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
interface ConfigurationModule {

  @Binds
  fun provideEnvironmentConfig(environmentConfigImpl: EnvironmentConfigImpl): EnvironmentConfig

}