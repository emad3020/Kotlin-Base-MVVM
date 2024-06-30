package com.core.utils

data class BaseResponse<T>(
  val result: T,
  val detail: String
)
