package com.core.utils.connection

import android.content.Context
import android.net.NetworkCapabilities.TRANSPORT_CELLULAR
import android.net.NetworkCapabilities.TRANSPORT_WIFI
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ConnectivityManagerImpl @Inject constructor(
  @ApplicationContext
  private val context: Context
) : ConnectivityManager {

  override fun isNetworkAvailable(): Boolean {
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE)
      as? android.net.ConnectivityManager
    val network = connectivityManager?.activeNetwork
    val networkCapabilities = connectivityManager?.getNetworkCapabilities(network)

    return when {
      network == null -> false
      networkCapabilities == null -> false
      networkCapabilities.hasTransport(TRANSPORT_WIFI) or
        networkCapabilities.hasTransport(TRANSPORT_CELLULAR) -> true

      else -> false
    }
  }
}