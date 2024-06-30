package com.core.presentation.extensions

import androidx.navigation.NavController

fun NavController.navigateSafe(dest: Int) {
  runCatching {
    this.navigate(dest)
  }
}

fun NavController.popBackSafe() {
  runCatching {
    this.popBackStack()
  }
}