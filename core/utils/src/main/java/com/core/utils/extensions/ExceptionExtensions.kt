package com.core.utils.extensions

fun stateException(errorMessage: String? = null): Nothing = errorMessage?.let {
  throw IllegalStateException(it)
} ?: throw IllegalStateException()

fun stateException(exception: Exception? = null): Nothing = exception?.let {
  throw IllegalStateException(it)
} ?: throw IllegalStateException()

class EmptyDatabaseException : Exception()