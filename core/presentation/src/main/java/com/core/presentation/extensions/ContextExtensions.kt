package com.core.presentation.extensions

import android.content.Context
import android.widget.Toast
import com.core.presentation.R

fun Context.showMessage(message: String?, duration: Int = Toast.LENGTH_LONG) {
  Toast.makeText(
    this,
    message ?: resources.getString(R.string.some_error),
    duration
  ).show()
}