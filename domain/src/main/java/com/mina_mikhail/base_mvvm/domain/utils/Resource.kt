package com.mina_mikhail.base_mvvm.domain.utils

sealed class Resource<out T> {

  class Success<out T>(val value: T) : Resource<T>()

  class Failure(
    val failureStatus: FailureStatus,
    val code: Int? = null,
    val message: String? = null
  ) : Resource<Nothing>()

  data object Loading : Resource<Nothing>()

  data object Default : Resource<Nothing>()
}