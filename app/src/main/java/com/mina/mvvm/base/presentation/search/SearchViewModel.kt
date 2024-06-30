package com.mina.mvvm.base.presentation.search

import com.core.presentation.base.BaseViewModel
import com.mina.mvvm.base.domain.repository.SearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
  private val searchRepository: SearchRepository
) : BaseViewModel()