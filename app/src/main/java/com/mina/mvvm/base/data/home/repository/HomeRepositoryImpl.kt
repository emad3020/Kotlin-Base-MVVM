package com.mina.mvvm.base.data.home.repository

import com.mina.mvvm.base.data.home.data_source.remote.HomeRemoteDataSource
import com.mina.mvvm.base.domain.repository.HomeRepository
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(private val remoteDataSource: HomeRemoteDataSource) :
  HomeRepository