package com.mina.mvvm.base.data.general.repository

import com.mina.mvvm.base.data.general.data_source.remote.GeneralRemoteDataSource
import com.mina.mvvm.base.data.local.preferences.AppPreferences
import com.mina.mvvm.base.domain.general.repository.GeneralRepository
import javax.inject.Inject

class GeneralRepositoryImpl @Inject constructor(
  private val remoteDataSource: GeneralRemoteDataSource,
  private val appPreferences: AppPreferences
) : GeneralRepository