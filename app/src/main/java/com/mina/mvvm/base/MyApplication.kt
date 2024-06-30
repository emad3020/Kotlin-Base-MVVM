package com.mina.mvvm.base

import android.content.Context
import androidx.multidex.MultiDex
import com.mina.base.mvvm.BuildConfig
import com.zeugmasolutions.localehelper.LocaleAwareApplication
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber.*
import timber.log.Timber.Forest.plant


@HiltAndroidApp
class MyApplication : LocaleAwareApplication() {


  override fun onCreate() {
    super.onCreate()

    if (BuildConfig.DEBUG) {
      plant(DebugTree())
    }
  }


  override fun attachBaseContext(base: Context) {
    super.attachBaseContext(base)

    MultiDex.install(this)
  }

}