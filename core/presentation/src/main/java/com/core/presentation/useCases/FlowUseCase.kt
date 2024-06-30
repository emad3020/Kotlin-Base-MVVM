package com.core.presentation.useCases

import com.core.presentation.extensions.obtainOutcome
import com.core.utils.Resource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

abstract class FlowUseCase<in Params, Result>(private val coroutineDispatcher: CoroutineDispatcher) {
  operator fun invoke(parameters: Params): Flow<Resource<Result>> =
    execute(parameters)
      .obtainOutcome()
      .flowOn(coroutineDispatcher)

  protected abstract fun execute(parameters: Params): Flow<Result>
}