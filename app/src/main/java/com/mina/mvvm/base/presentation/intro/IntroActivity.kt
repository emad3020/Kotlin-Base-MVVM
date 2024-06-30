package com.mina.mvvm.base.presentation.intro

import com.core.presentation.R
import com.core.presentation.base.BaseActivity
import com.mina.base.mvvm.databinding.ActivityIntroBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class IntroActivity : BaseActivity<ActivityIntroBinding>(
  ActivityIntroBinding::inflate
) {
  override var isRequiredScreenOn: Boolean = true

}