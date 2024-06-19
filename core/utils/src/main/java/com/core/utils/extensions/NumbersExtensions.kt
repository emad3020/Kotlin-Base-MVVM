package com.core.utils.extensions

import java.util.Locale

fun Int?.formatCentsToTens(): Double = (this?.toDouble() ?: 0.0) / 100.0

fun Double.toMoneyFormat() = String.format(
  Locale.ENGLISH,
  "%.2f",
  this
)

fun Int?.formatCentsAmount(): String {
  return String.format(
    Locale.ENGLISH,
    "%,.2f",
    this.formatCentsToTens()
  )
}

fun IntRange.randomNumber() = shuffled().distinct().joinToString("").toLong()
