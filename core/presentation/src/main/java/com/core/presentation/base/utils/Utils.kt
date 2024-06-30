package com.core.presentation.base.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.provider.Settings.Secure
import android.util.Patterns
import android.widget.Toast
import com.core.presentation.R
import com.tapadoo.alerter.Alerter

fun showMessage(context: Context, message: String?) {
  Toast.makeText(context, message ?: context.resources.getString(R.string.some_error), Toast.LENGTH_SHORT)
    .show()
}

fun showNoInternetAlert(activity: Activity) {
  Alerter.create(activity)
    .setTitle(activity.resources.getString(R.string.connection_error))
    .setText(activity.resources.getString(R.string.no_internet))
    .setIcon(R.drawable.ic_no_internet)
    .setBackgroundColorRes(R.color.red)
    .enableClickAnimation(true)
    .enableSwipeToDismiss()
    .show()
}

fun hideLoadingDialog(mProgressDialog: Dialog?, activity: Activity?) {
  if (activity != null && !activity.isFinishing && mProgressDialog != null && mProgressDialog.isShowing) {
    mProgressDialog.dismiss()
  }
}

@SuppressLint("HardwareIds")
fun getDeviceId(context: Context): String {
  return Secure.getString(context.contentResolver, Secure.ANDROID_ID)
}

fun String.isEmailValid(): Boolean = Patterns.EMAIL_ADDRESS.matcher(this).matches()