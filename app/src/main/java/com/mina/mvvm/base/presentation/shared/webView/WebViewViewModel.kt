package com.mina.mvvm.base.presentation.shared.webView

import com.core.presentation.base.BaseViewModel
import com.mina.mvvm.base.domain.general.repository.GeneralRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WebViewViewModel @Inject constructor(
  private val generalRepository: GeneralRepository
) : BaseViewModel()