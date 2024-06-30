package com.mina.mvvm.base.data.home.data_source.remote

import com.mina.mvvm.base.data.remote.BaseRemoteDataSource
import javax.inject.Inject

class HomeRemoteDataSource @Inject constructor(private val apiService: HomeServices) :
  BaseRemoteDataSource()