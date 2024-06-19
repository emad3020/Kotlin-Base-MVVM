package com.core.utils.providers

import android.content.Context
import android.content.res.ColorStateList
import android.content.res.Resources
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.text.style.UnderlineSpan
import android.view.View
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import com.core.utils.enums.Languages
import dagger.hilt.android.qualifiers.ApplicationContext
import java.util.Locale
import javax.inject.Inject

class ResourceProviderImpl @Inject constructor(
  @ApplicationContext
  private val context: Context
) : ResourceProvider {
  override fun getColor(colorRes: Int): Int = ContextCompat.getColor(context, colorRes)

  override fun getColorStateList(colorRes: Int): ColorStateList =
    AppCompatResources.getColorStateList(context, colorRes)

  override fun getDrawable(drawableRes: Int): Drawable =
    checkNotNull(ContextCompat.getDrawable(context, drawableRes))

  override fun getString(resId: Int, vararg values: Any): String = context.getString(resId, *values)

  override fun getLocale(): Locale = context.resources.configuration.locales.get(0)

  override fun isArabicLocale(): Boolean =
    getLocale().language == Languages.ARABIC.languageCode

  override fun getSystemResources(): Resources = context.resources

  override fun loadRawResource(fileName: String): String? = try {
    context.assets.open(fileName)
      .bufferedReader()
      .use { it.readText() }
  } catch (e: Exception) {
    null
  }

  override fun getSpannableString(
    spannableString: String,
    beforeTextResId: Int,
    spanColorRes: Int,
    afterTextResId: Int?,
    isUnderlined: Boolean,
    isBold: Boolean,
    spannableListener: (() -> Unit)?
  ): Spannable {
    val beforeString = context.getString(beforeTextResId)
    val afterString = afterTextResId?.let { context.getString(it) } ?: run { "" }

    val finalMessage = "$beforeString $spannableString $afterString"

    val spannable = SpannableString(finalMessage).apply {
      setSpan(
        object : ClickableSpan() {
          override fun onClick(textView: View) {
            spannableListener?.invoke()
          }
        },
        finalMessage.indexOf(spannableString),
        finalMessage.indexOf(spannableString) + spannableString.length,
        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
      )

      setSpan(
        ForegroundColorSpan(getColor(spanColorRes)),
        finalMessage.indexOf(spannableString),
        finalMessage.indexOf(spannableString) + spannableString.length,
        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
      )

      if (isUnderlined) {
        setSpan(
          UnderlineSpan(),
          finalMessage.indexOf(spannableString),
          finalMessage.indexOf(spannableString) + spannableString.length,
          Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
      }
      if (isBold) {
        setSpan(
          StyleSpan(Typeface.BOLD),
          finalMessage.indexOf(spannableString),
          finalMessage.indexOf(spannableString) + spannableString.length,
          Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
      }
    }

    return spannable
  }
}