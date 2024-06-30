package com.core.presentation.listeners

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import com.core.utils.enums.Languages
import com.core.utils.extensions.steamingMoneyAmount
import com.core.utils.extensions.toBigDecimal
import java.lang.ref.WeakReference
import java.math.BigDecimal
import java.math.RoundingMode
import java.text.NumberFormat
import java.util.Locale

class MoneyTextWatcher(
  private val locale: Locale = Locale(Languages.ENGLISH.languageCode, "EG"),
  private val editText: WeakReference<EditText>,
  private var afterFormattingFinished: ((moneyAmount: String) -> Unit)? = null
) : TextWatcher {

  override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
  }

  override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
  }

  override fun afterTextChanged(s: Editable) {
    val et = editText.get() ?: return
    et.removeTextChangedListener(this)
    val parsed: BigDecimal = try {
      s.toString().toBigDecimal()
    } catch (e: NumberFormatException) {
      "0.00".toBigDecimal()
    }
    var formatted = NumberFormat.getCurrencyInstance(locale).format(parsed)
    formatted = formatted.replace("EGP", "")
    et.apply {
      setText(formatted)
      setSelection(formatted.length)
      addTextChangedListener(this@MoneyTextWatcher)
    }

    afterFormattingFinished?.invoke(formatted.steamingMoneyAmount())
  }
}