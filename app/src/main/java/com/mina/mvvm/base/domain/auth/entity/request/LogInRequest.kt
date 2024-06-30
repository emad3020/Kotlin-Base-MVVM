package com.mina.mvvm.base.domain.auth.entity.request

data class LogInRequest(
  var email: String,
  var password: String
) {
  constructor() : this("", "")
}

class LogInValidationException(private val validationType: String) : Exception(validationType)