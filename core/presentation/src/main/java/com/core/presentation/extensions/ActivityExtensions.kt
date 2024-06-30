package com.core.presentation.extensions

import android.app.Activity
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.activity.OnBackPressedCallback
import androidx.annotation.DrawableRes
import androidx.annotation.RawRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import codes.core.shared.prettyPopUp.PrettyPopUpHelper
import com.core.presentation.R
import com.tapadoo.alerter.Alerter
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

fun <T> AppCompatActivity.collect(sharedFlow: SharedFlow<T>, block: (T) -> Unit) {
  lifecycleScope.launch {
    sharedFlow
      .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED) // Same as repeatOnLifeCycle()
      .collect {
        block(it)
      }
  }
}

fun <T> Activity.openActivity(
  targetScreen: Class<T>,
  clearStack: Boolean = false,
  closePreviousScreen: Boolean = false,
  data: Bundle? = null
) {
  val intent = Intent(this, targetScreen).apply {
    if (clearStack) {
      flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    }
  }

  data?.let {
    intent.putExtras(it)
  }

  startActivity(intent)
  if (clearStack || closePreviousScreen) {
    finish()
  }
}

fun Activity.hideKeyboard() {
  var view = currentFocus
  if (view == null) view = View(this)
  val imm = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
  imm.hideSoftInputFromWindow(view.windowToken, 0)
}

fun AppCompatActivity.showNoInternetAlert() {
  Alerter.create(this)
    .setTitle(resources.getString(R.string.connection_error))
    .setText(resources.getString(R.string.no_internet))
    .setIcon(R.drawable.ic_no_internet)
    .setBackgroundColorRes(R.color.red)
    .enableClickAnimation(true)
    .enableSwipeToDismiss()
    .show()
}

fun AppCompatActivity.showErrorAlert(
  title: String,
  message: String,
  @DrawableRes iconId: Int = R.drawable.ic_help,
  duration: Long = TimeUnit.SECONDS.toMillis(10)
) {
  Alerter.create(this)
    .setTitle(title)
    .setText(message)
    .setIcon(iconId)
    .setBackgroundColorRes(R.color.red)
    .enableClickAnimation(true)
    .setDuration(duration)
    .enableSwipeToDismiss()
    .show()
}

fun AppCompatActivity.onBackButtonPressed(callback: (() -> Unit)? = null) {
  val onBackPressed: OnBackPressedCallback = object : OnBackPressedCallback(true) {
    override fun handleOnBackPressed() {
      callback?.let { callback() } ?: run { finish() }
    }
  }
  this.onBackPressedDispatcher.addCallback(this, onBackPressed)
}

fun <A : Activity> Activity.openActivityAndClearStack(activity: Class<A>) {
  Intent(this, activity).apply {
    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    startActivity(this)
    finish()
  }
}

fun <A : Activity> Activity.openActivity(activity: Class<A>) {
  Intent(this, activity).apply {
    startActivity(this)
  }
}

fun AppCompatActivity.showPrettyPopUp(
  isCancelable: Boolean = true,
  title: String? = null,
  content: String,
  dialogIcon: Drawable? = null,
  positiveButtonText: String ? = null,
  positiveButtonClick: (() -> Unit)? = null,
  negativeButtonText: String? = null,
  negativeButtonClick: (() -> Unit)? = null,
  neutralText: String? = null,
  neutralButtonClick: (() -> Unit)? = null,
  @RawRes animationFile: Int? = null
): PrettyPopUpHelper =
  PrettyPopUpHelper.Builder(this.supportFragmentManager)
    .setIsCancelable(isCancelable)
    .setTitle(title)
    .setDialogIcon(dialogIcon)
    .setTitleColor(codes.core.shared.prettyPopUp.R.color.colorBlack)
    .setContent(content)
    .setContentColor(R.color.darkGray)
    .setAnimationFile(animationFile)
    .setPositiveButtonTextColor(codes.core.shared.prettyPopUp.R.color.colorGray)
    .setNeutralButton(neutralText) {
      it.dismiss()
      neutralButtonClick?.invoke()
    }
    .setPositiveButton(positiveButtonText) {
      it.dismiss()

      positiveButtonClick?.invoke()
    }
    .setNegativeButton(negativeButtonText) {
      it.dismiss()

      negativeButtonClick?.invoke()
    }
    .create()