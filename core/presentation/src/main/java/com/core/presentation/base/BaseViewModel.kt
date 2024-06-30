package com.core.presentation.base

import androidx.lifecycle.ViewModel
import com.core.presentation.base.utils.SingleLiveEvent

open class BaseViewModel : ViewModel() {

  var dataLoadingEvent: SingleLiveEvent<Int> = SingleLiveEvent()
}