package com.mina.mvvm.base.presentation.splash

import android.os.Handler
import android.os.Looper
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import com.core.presentation.base.BaseActivity
import com.core.presentation.extensions.openActivityAndClearStack
import com.mina.base.mvvm.databinding.ActivitySplashBinding
import com.mina.mvvm.base.presentation.home.HomeActivity
import com.mina.mvvm.base.presentation.intro.IntroActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : BaseActivity<ActivitySplashBinding>(
  ActivitySplashBinding::inflate
) {

  private val viewModel: SplashViewModel by viewModels()
  override var isPortraitOrientation: Boolean = true

  override fun ActivitySplashBinding.initializeUI() {
    decideNavigationLogic()
  }

  private fun decideNavigationLogic() {
    Handler(Looper.getMainLooper()).postDelayed({
      val targetActivity = if (viewModel.isFirstTime()) {
        IntroActivity::class.java
      } else {
        HomeActivity::class.java
      }
      openActivityAndClearStack(targetActivity)
    }, 2000)
  }
}