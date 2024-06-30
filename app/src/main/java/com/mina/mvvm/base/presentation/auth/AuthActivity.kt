package com.mina.mvvm.base.presentation.auth

import com.core.presentation.R
import com.core.presentation.base.BaseActivity
import com.mina.base.mvvm.databinding.ActivityAuthBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthActivity : BaseActivity<ActivityAuthBinding>(
  ActivityAuthBinding::inflate
) {
  override var isPortraitOrientation: Boolean = true
}