package com.core.presentation.base

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.Settings.ACTION_WIFI_SETTINGS
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.core.presentation.R
import com.core.presentation.extensions.showPrettyPopUp
import com.core.utils.connection.ConnectivityManager
import com.core.utils.providers.ResourceProvider
import okhttp3.internal.http2.Settings
import javax.inject.Inject

abstract class BaseFragment<VB : ViewBinding, VM : BaseViewModel>(
  private val bindingInflater: (inflater: LayoutInflater) -> VB
) : Fragment() {

  protected abstract val viewModel: VM

  @Inject
  lateinit var connectivityManager: ConnectivityManager

  @Inject
  lateinit var resourceProvider: ResourceProvider

  private val loadingDialogDelegate = lazy { LoadingDialog(activity) }
  private val loadingDialog by loadingDialogDelegate

  // View Binding Instance
  private var _binding: VB? = null
  open val binding get() = checkNotNull(_binding)

  override fun onResume() {
    super.onResume()

    registerListeners()

    if (!connectivityManager.isNetworkAvailable()) {
      showPrettyPopUp(
        title = "No Internet Connection",
        content = "You don't have any connection active,plase turn WIFI on or use cellular data",
        isCancelable = false,
        animationFile = com.core.utils.R.raw.wifi_animation,
        neutralText = "Setting",
        positiveButtonText = "Close",
        positiveButtonClick = {
          requireActivity().finish()
        },
        neutralButtonClick = {
          if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            startActivity(Intent(android.provider.Settings.Panel.ACTION_WIFI))
          } else {
            startActivity(Intent(ACTION_WIFI_SETTINGS))
          }
        }
      )
    }
  }

  override fun onPause() {
    unRegisterListeners()

    super.onPause()
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    _binding = bindingInflater(layoutInflater)

    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    getFragmentArguments()
    binding.initializeUI()
    setupObservers()
  }

  open fun registerListeners() {}

  open fun unRegisterListeners() {}

  open fun getFragmentArguments() {}

  open fun VB.initializeUI() {}

  open fun setupObservers() {}

  fun showLoading(hint: String? = null) = loadingDialog.showDialog(hint)

  fun hideLoading() = loadingDialog.hideDialog()

  override fun onDestroyView() {
    _binding = null
    super.onDestroyView()
  }

  override fun onDestroy() {
    if (loadingDialogDelegate.isInitialized()) {
      loadingDialog.clean()
    }
    super.onDestroy()
  }
}