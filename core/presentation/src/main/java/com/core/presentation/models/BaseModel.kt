package com.core.presentation.models

import androidx.annotation.Keep
import java.util.Random

@Keep
open class BaseModel(
  open val nextPage: String? = null,
  open val previousPage: String? = null,
  open val itemId: String = Random().nextInt().toString()
) {
  override fun hashCode(): Int {
    return super.hashCode()
  }

  override fun equals(other: Any?): Boolean {
    return super.equals(other)
  }
}