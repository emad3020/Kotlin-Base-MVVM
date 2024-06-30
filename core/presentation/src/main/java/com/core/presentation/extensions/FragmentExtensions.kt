package com.core.presentation.extensions

import android.app.Activity
import android.graphics.drawable.Drawable
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.RawRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import codes.core.shared.prettyPopUp.PrettyPopUpHelper
import com.core.presentation.R
import com.core.presentation.base.utils.hideSoftInput
import com.core.presentation.base.utils.showNoInternetAlert
import com.core.presentation.databinding.FullScreenErrorBinding
import com.core.utils.Resource
import com.core.utils.validation.BusinessValidationException
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import java.nio.channels.UnresolvedAddressException

fun Fragment.showPrettyPopUp(
  isCancelable: Boolean = true,
  title: String? = null,
  content: String,
  dialogIcon: Drawable? = null,
  positiveButtonText: String? = null,
  positiveButtonClick: (() -> Unit)? = null,
  negativeButtonText: String? = null,
  negativeButtonClick: (() -> Unit)? = null,
  neutralText: String? = null,
  neutralButtonClick: (() -> Unit)? = null,
  @RawRes animationFile: Int? = null
): PrettyPopUpHelper? =
  (this.requireActivity() as? AppCompatActivity)?.showPrettyPopUp(
    isCancelable = isCancelable,
    title = title,
    content = content,
    dialogIcon = dialogIcon,
    animationFile = animationFile,
    positiveButtonText = positiveButtonText,
    positiveButtonClick = positiveButtonClick,
    negativeButtonText = negativeButtonText,
    negativeButtonClick = negativeButtonClick,
    neutralText = neutralText,
    neutralButtonClick = neutralButtonClick
  )

fun <T> Fragment.collect(sharedFlow: SharedFlow<T>, block: (T) -> Unit) {
  viewLifecycleOwner.lifecycleScope.launch {
    sharedFlow
      .flowWithLifecycle(viewLifecycleOwner.lifecycle, Lifecycle.State.STARTED)
      .collect {
        block(it)
      }
  }
}

fun Fragment.handleApiError(
  failure: Resource.Failure,
  businessValidationExceptionAction: ((invalidField: Int?) -> Unit)? = null,
  failureAction: (() -> Unit)? = null,
  noInternetAction: (() -> Unit)? = null
) {
  when (failure.exception) {
    is BusinessValidationException -> {
      businessValidationExceptionAction?.invoke(failure.exception.message?.toInt())
    }

    is UnresolvedAddressException -> {
      noInternetAction?.invoke()
      showNoInternetAlert()
    }

    else -> {
      val message = failure.exception.message
        ?: failure.exception.localizedMessage
        ?: resources.getString(R.string.some_error)

      showErrorAlert(
        title = resources.getString(R.string.error),
        message = message
      )

      failureAction?.invoke()
    }
  }
}

fun Fragment.showFullScreenError(
  fullScreenViewGroup: ViewGroup,
  message: String?,
  enableCloseButton: Boolean = false,
  retryCallbacks: (() -> Unit)? = null,
  closeCallback: (() -> Unit)? = null
) {
  val errorBinding = FullScreenErrorBinding.inflate(layoutInflater, fullScreenViewGroup, false)
  errorBinding.apply {
    txtErrorMessage.text = message ?: resources.getString(R.string.some_error)
    btnClose.isVisible = enableCloseButton
    /**
     *  Add availability to use to remove the error view
     * **/
    btnClose.setOnClickListener {
      closeCallback?.invoke()
      fullScreenViewGroup.removeView(errorBinding.root)
    }

    retryCallbacks?.let { retry ->
      btnTryAgain.setOnClickListener {
        retry.invoke()
        fullScreenViewGroup.removeView(errorBinding.root)
      }
    } ?: run { errorBinding.btnTryAgain.hide() }
  }

  fullScreenViewGroup.addView(errorBinding.root, 0)
//    setConstrainLayoutToParent(errorBinding)
}

fun Fragment.hideKeyboard() {
  requireActivity().hideKeyboard()
}

fun Fragment.showNoInternetAlert() = (this.requireActivity() as? AppCompatActivity)?.showNoInternetAlert()

fun Fragment.showErrorAlert(title: String, message: String) =
  (this.requireActivity() as? AppCompatActivity)?.showErrorAlert(title, message)

fun Fragment.showMessage(message: String?, duration: Int = Toast.LENGTH_LONG) =
  requireContext().showMessage(message, duration)

fun Fragment.showError(
  message: String,
  retryActionName: String? = null,
  action: (() -> Unit)? = null
) = requireView().showSnackBar(message, retryActionName, action)


fun Fragment.getMyColor(@ColorRes id: Int) = ContextCompat.getColor(requireContext(), id)

fun Fragment.getMyDrawable(@DrawableRes id: Int) = ContextCompat.getDrawable(requireContext(), id)!!

fun Fragment.getMyString(id: Int) = resources.getString(id)

fun <A : Activity> Fragment.openActivityAndClearStack(activity: Class<A>) {
  requireActivity().openActivityAndClearStack(activity)
}

fun <A : Activity> Fragment.openActivity(activity: Class<A>) {
  requireActivity().openActivity(activity)
}

fun <T> Fragment.getNavigationResultLiveData(key: String = "result") =
  findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<T>(key)

fun <T> Fragment.removeNavigationResultObserver(key: String = "result") =
  findNavController().currentBackStackEntry?.savedStateHandle?.remove<T>(key)

fun <T> Fragment.setNavigationResult(result: T, key: String = "result") {
  findNavController().previousBackStackEntry?.savedStateHandle?.set(key, result)
}

fun Fragment.onBackPressedCustomAction(action: () -> Unit) {
  requireActivity().onBackPressedDispatcher.addCallback(
    viewLifecycleOwner,
    object : OnBackPressedCallback(true) {
      override
      fun handleOnBackPressed() {
        action()
      }
    }
  )
}

fun Fragment.navigateSafe(directions: NavDirections, navOptions: NavOptions? = null) {
  findNavController().navigate(directions, navOptions)
}

fun Fragment.backToPreviousScreen() {
  findNavController().navigateUp()
}