package com.core.presentation.base

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import androidx.viewbinding.ViewBinding
import com.core.presentation.R

abstract class BaseDialog<VB : ViewBinding> (
  open val bindingInflater: (LayoutInflater) -> VB
) : DialogFragment() {

  // View Binding Instance
  private var _binding: VB? = null
  open val binding get() = checkNotNull(_binding)

  private val loadingDialogDelegate = lazy { LoadingDialog(activity) }
  private val loadingDialog by loadingDialogDelegate

  override fun getTheme() = R.style.CustomDialogAnimation

  open fun registerListeners() {}

  open fun unRegisterListeners() {}

  open fun VB.initializeUI() {}

  open fun setupObservers() {}

  override fun onResume() {
    super.onResume()

    registerListeners()
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
    dialog?.window?.apply {
      val windowAttributes = attributes
      windowAttributes.gravity = Gravity.CENTER
      attributes = windowAttributes
      requestFeature(Window.FEATURE_NO_TITLE)

      setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }
    dialog?.setCanceledOnTouchOutside(true)

    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    binding.initializeUI()
    setupObservers()
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