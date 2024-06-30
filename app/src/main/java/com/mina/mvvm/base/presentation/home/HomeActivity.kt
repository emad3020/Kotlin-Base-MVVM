package com.mina.mvvm.base.presentation.home

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.annotation.IdRes
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.core.presentation.base.BaseActivity
import com.mina.base.mvvm.R
import com.mina.base.mvvm.databinding.ActivityHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : BaseActivity<ActivityHomeBinding>(
  ActivityHomeBinding::inflate
) {

  companion object {
    const val ACTION_OPEN_SPECIFIC_PAGE = "ACTION_OPEN_SPECIFIC_PAGE"
    const val TAB_ID = "TAB_ID"
  }

  private var isReceiverRegistered = false
  override var isPortraitOrientation: Boolean = true

  private val openSpecificTabReceiver: BroadcastReceiver = object : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
      navigateToSpecificTab(intent.getIntExtra(TAB_ID, 0))
    }
  }

  override fun ActivityHomeBinding.initializeUI() {
    setUpNavController()
    setupBottomNavigation()
  }


  override fun onResume() {
    super.onResume()

    registerOpenSpecificTabReceiver()
  }

  private fun registerOpenSpecificTabReceiver() {
    if (!isReceiverRegistered) {
      LocalBroadcastManager.getInstance(this)
        .registerReceiver(
          openSpecificTabReceiver,
          IntentFilter(ACTION_OPEN_SPECIFIC_PAGE)
        )
      isReceiverRegistered = true
    }
  }

  private fun navigateToSpecificTab(@IdRes tabID: Int) {
    binding.bottomNavigationView.selectedItemId = tabID
  }

  private fun setUpNavController() {
    val navHostFragment = supportFragmentManager.findFragmentById(
      R.id.fragment_host_container
    ) as NavHostFragment
    navController = navHostFragment.navController
  }

  private fun setupBottomNavigation() {
    navController?.let {
      binding.bottomNavigationView.apply {
        setupWithNavController(it)
      }
    }
  }

  override fun onDestroy() {
    unregisterOpenSpecificTabReceiver()

    super.onDestroy()
  }

  private fun unregisterOpenSpecificTabReceiver() {
    LocalBroadcastManager.getInstance(this)
      .unregisterReceiver(openSpecificTabReceiver)
  }
}