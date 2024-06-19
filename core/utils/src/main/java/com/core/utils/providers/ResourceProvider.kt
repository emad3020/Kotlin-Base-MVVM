package com.core.utils.providers

import android.content.res.ColorStateList
import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.text.Spannable
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import java.util.Locale

interface ResourceProvider {

  fun getColor(
    @ColorRes colorRes: Int
  ): Int

  fun getColorStateList(
    @ColorRes colorRes: Int
  ): ColorStateList

  fun getDrawable(
    @DrawableRes drawableRes: Int
  ): Drawable

  fun getString(
    @StringRes resId: Int,
    vararg values: Any
  ): String

  fun getLocale(): Locale

  fun isArabicLocale(): Boolean

  fun getSystemResources(): Resources

  fun loadRawResource(fileName: String): String?

  fun getSpannableString(
    spannableString: String,
    @StringRes beforeTextResId: Int,
    @ColorRes spanColorRes: Int,
    @StringRes afterTextResId: Int? = null,
    isUnderlined: Boolean = false,
    isBold: Boolean = false,
    spannableListener: (() -> Unit)? = null
  ): Spannable
}