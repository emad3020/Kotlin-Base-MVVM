package com.core.presentation.extensions

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.core.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import timber.log.Timber
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

internal const val DEFAULT_TIMEOUT = 5000L

fun <T> Flow<T>.obtainOutcome(): Flow<Resource<T>> = this.map { Resource.success(it) }
  .onStart { emit(Resource.Loading) }
  .catch { e ->
    Timber.e("Flow throw exception with :",e.message)
    emit(Resource.Failure(e))
  }

@JvmOverloads
fun <T> Flow<T?>.toSafeLiveDate(
  context: CoroutineContext = EmptyCoroutineContext,
  timeoutInMs: Long = DEFAULT_TIMEOUT
): LiveData<T> = liveData(context, timeoutInMs) {
  collect { value ->
    value?.let { emit(it) }
  }
}