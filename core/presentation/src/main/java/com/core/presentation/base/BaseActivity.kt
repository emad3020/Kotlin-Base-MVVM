package com.core.presentation.base

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.viewbinding.ViewBinding
import com.core.presentation.BuildConfig
import com.core.utils.connection.ConnectivityManager
import com.zeugmasolutions.localehelper.LocaleHelper
import com.zeugmasolutions.localehelper.LocaleHelperActivityDelegate
import com.zeugmasolutions.localehelper.LocaleHelperActivityDelegateImpl
import java.util.Locale
import javax.inject.Inject

abstract class BaseActivity<VB : ViewBinding> (
  private val bindingInflater: (LayoutInflater) -> VB
) : AppCompatActivity() {

  @Inject lateinit var connectivityManager: ConnectivityManager

  // View Binding Instance
  private var _binding: VB? = null
  open val binding get() = checkNotNull(_binding)

  // Nav Controller
  var navController: NavController? = null
  var navHostFragment: NavHostFragment? = null

  open var isRequiredScreenOn: Boolean = false
  open var isPortraitOrientation: Boolean = false

  private val loadingDialogDelegate = lazy { LoadingDialog(this) }
  private val loadingDialog by loadingDialogDelegate

  // Localization
  private val localeDelegate: LocaleHelperActivityDelegate = LocaleHelperActivityDelegateImpl()

  override fun getDelegate() = localeDelegate.getAppCompatDelegate(super.getDelegate())

  override fun attachBaseContext(newBase: Context) {
    super.attachBaseContext(localeDelegate.attachBaseContext(newBase))
  }

  override fun getApplicationContext(): Context =
    localeDelegate.getApplicationContext(super.getApplicationContext())

  override fun createConfigurationContext(overrideConfiguration: Configuration): Context {
    val context = super.createConfigurationContext(overrideConfiguration)
    return LocaleHelper.onAttach(context)
  }

  override fun onResume() {
    super.onResume()

    localeDelegate.onResumed(this)

    registerListeners()
    setUpObservers()
  }

  override fun onPause() {
    unRegisterListeners()

    super.onPause()

    localeDelegate.onPaused()
  }

  @SuppressLint("SourceLockedOrientationActivity")
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    if (isPortraitOrientation) {
      requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    }

    _binding = bindingInflater(layoutInflater)

    setContentView(binding.root)

    localeDelegate.onCreate(this)

    binding.initializeUI()

    if (BuildConfig.DEBUG || isRequiredScreenOn) {
      window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
    }
  }

  open fun registerListeners() {}

  open fun setUpObservers() {}

  open fun unRegisterListeners() {}

  open fun VB.initializeUI() {}

  open fun updateLocale(language: String) {
    localeDelegate.setLocale(this, Locale(language))
  }

  override fun onSupportNavigateUp(): Boolean {
    if (!(navController?.navigateUp() == true || super.onSupportNavigateUp())) {
      onBackPressedDispatcher.onBackPressed()
    }
    return true
  }

  fun showLoading(hint: String? = null) = loadingDialog.showDialog(hint)

  fun hideLoading() = loadingDialog.hideDialog()

  override fun onDestroy() {
    _binding = null
    if (loadingDialogDelegate.isInitialized()) {
      loadingDialog.clean()
    }

    super.onDestroy()
  }
}