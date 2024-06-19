package com.core.utils.extensions

inline fun <E : Any, T : Collection<E>> T?.whenNotNullNorEmpty(func: (T?) -> Unit) {
  if (!this.isNullOrEmpty()) {
    func(this)
  } else {
    func(null)
  }
}

fun <E : Any, T : Collection<E>> T?.whenNotNullNorEmpty(): T? {
  return if (!this.isNullOrEmpty()) {
    this
  } else {
    null
  }
}