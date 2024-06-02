package com.mina_mikhail.base_mvvm.presentation.home

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.annotation.IdRes
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.navigation.ui.setupWithNavController
import com.mina_mikhail.base_mvvm.presentation.R
import com.mina_mikhail.base_mvvm.presentation.base.BaseActivity
import com.mina_mikhail.base_mvvm.presentation.databinding.ActivityHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : BaseActivity<ActivityHomeBinding>() {

  companion object {
    const val ACTION_OPEN_SPECIFIC_PAGE = "ACTION_OPEN_SPECIFIC_PAGE"
    const val TAB_ID = "TAB_ID"
  }

  private var isReceiverRegistered = false

  private val openSpecificTabReceiver: BroadcastReceiver = object : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
      navigateToSpecificTab(intent.getIntExtra(TAB_ID, 0))
    }
  }

  override fun getLayoutId() = R.layout.activity_home

  override fun setUpViews() {
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
    navController.let {
      binding.bottomNavigationView.apply {
        inflateMenu(R.menu.menu_bottom_navigation)
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