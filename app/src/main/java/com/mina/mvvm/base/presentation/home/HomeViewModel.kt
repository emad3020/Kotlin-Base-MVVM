package com.mina.mvvm.base.presentation.home

import com.core.presentation.base.BaseViewModel
import com.core.presentation.base.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : BaseViewModel() {

  val showActionChooser = SingleLiveEvent<Void>()
  val showPrettyPopUp = SingleLiveEvent<Void>()

  fun onShowActionChooserClicked() {
    showActionChooser.call()
  }

  fun onShowPrettyPopUpClicked() {
    showPrettyPopUp.call()
  }
}