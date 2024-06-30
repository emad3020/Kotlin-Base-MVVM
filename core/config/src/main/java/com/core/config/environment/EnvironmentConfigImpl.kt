package com.core.config.environment

import com.core.config.enums.Country
import javax.inject.Inject

class EnvironmentConfigImpl @Inject constructor() : EnvironmentConfig{

  override fun getCountry(): String = Country.EGYPT.code

}