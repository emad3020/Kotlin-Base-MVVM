package com.mina.mvvm.base.data.general.data_source.remote

import com.mina.mvvm.base.data.remote.BaseRemoteDataSource
import javax.inject.Inject

class GeneralRemoteDataSource @Inject constructor(
  private val apiService: GeneralServices
) : BaseRemoteDataSource()