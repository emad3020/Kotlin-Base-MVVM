package com.mina.mvvm.base.data.search.data_source.remote

import com.mina.mvvm.base.data.remote.BaseRemoteDataSource
import javax.inject.Inject

class SearchRemoteDataSource @Inject constructor(
  private val apiService: SearchServices
) : BaseRemoteDataSource()