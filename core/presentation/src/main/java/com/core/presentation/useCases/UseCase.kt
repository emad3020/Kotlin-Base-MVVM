package com.core.presentation.useCases


import com.core.utils.Resource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import timber.log.Timber

abstract class UseCase<in Params, Result>(private val coroutineDispatcher: CoroutineDispatcher) {
  suspend operator fun invoke(parameters: Params): Resource<Result> =
    withContext(coroutineDispatcher) {
      try {
        Resource.Success(execute(parameters))
      } catch (e: Throwable) {
        Timber.e("Flow throw exception with :",e.message)
        Resource.Failure(e)
      }
    }

  protected abstract suspend fun execute(parameters: Params): Result
}