package com.mina.mvvm.base.data.search.repository

import com.mina.mvvm.base.data.search.data_source.remote.SearchRemoteDataSource
import com.mina.mvvm.base.domain.repository.SearchRepository
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
  private val remoteDataSource: SearchRemoteDataSource
) : SearchRepository