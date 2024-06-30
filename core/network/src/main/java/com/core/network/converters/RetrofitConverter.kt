package com.core.network.converters

import retrofit2.Converter
import javax.inject.Inject

class RetrofitConverter @Inject constructor(
  private val jsonConverter: Converter.Factory
) : Converter.Factory()