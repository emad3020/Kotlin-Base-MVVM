package com.core.utils.validation

class BusinessValidationException(
  validationType: String
) : Exception(validationType)