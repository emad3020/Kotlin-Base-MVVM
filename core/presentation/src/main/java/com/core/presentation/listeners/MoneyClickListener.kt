package com.core.presentation.listeners

import android.view.View
import android.widget.EditText
import java.lang.ref.WeakReference

class MoneyClickListener(
  private val editText: WeakReference<EditText>
) : View.OnClickListener {
  override fun onClick(v: View) {
    editText.get()?.setSelection(editText.get()?.text?.length ?: 0)
  }
}