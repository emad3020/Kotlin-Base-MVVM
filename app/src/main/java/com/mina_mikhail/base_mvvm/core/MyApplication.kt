package com.mina_mikhail.base_mvvm.core

import android.content.Context
import androidx.multidex.MultiDex
import com.zeugmasolutions.localehelper.LocaleAwareApplication
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication : LocaleAwareApplication() {

  override
  fun attachBaseContext(base: Context) {
    super.attachBaseContext(base)

    MultiDex.install(this)
  }
}