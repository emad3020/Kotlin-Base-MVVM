package com.mina.mvvm.base.data.remote

import com.core.utils.BaseResponse
import com.core.utils.ErrorResponse
import com.core.utils.Resource
import com.core.utils.extensions.stateException
import com.core.utils.extensions.toModel
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import org.json.JSONObject
import retrofit2.HttpException
import java.net.ConnectException
import java.net.UnknownHostException
import javax.inject.Inject

open class BaseRemoteDataSource @Inject constructor() {

  suspend fun <T> safeApiCall(apiCall: suspend () -> T): Resource<T> {
    try {
      val apiResponse = apiCall.invoke()

      when ((apiResponse as BaseResponse<*>).result) {
        null -> stateException("")
        is List<*> -> {
          return if ((apiResponse.result as List<*>).isNotEmpty()) {
            Resource.Success(apiResponse)
          } else {
            stateException("")
          }
        }
        is Boolean -> {
          return if (apiResponse.result as Boolean) {
            Resource.Success(apiResponse)
          } else {
            stateException(apiResponse.detail)
          }
        }
        else -> {
          return Resource.Success(apiResponse)
        }
      }
    } catch (throwable: Throwable) {
      when (throwable) {
        is HttpException -> {
          when {
            throwable.code() == 422 -> {
              val jObjError = JSONObject(throwable.response()!!.errorBody()!!.string())
              val apiResponse = jObjError.toString()
              val response = Gson().fromJson(apiResponse, BaseResponse::class.java)

              stateException(response.detail)
            }
            throwable.code() == 401 -> {
              val errorResponse =
                throwable.response()?.errorBody()!!.charStream().readText().toModel(
                  ErrorResponse::class.java
                )

              stateException(errorResponse?.detail)
            }
            else -> {
              return if (throwable.response()?.errorBody()!!.charStream().readText().isEmpty()) {
                Resource.Failure(throwable)
              } else {
                try {
                  val errorResponse = Gson().fromJson(
                    throwable.response()?.errorBody()!!.charStream().readText(),
                    ErrorResponse::class.java
                  )

                  stateException(errorResponse.detail)
                } catch (ex: JsonSyntaxException) {
                  Resource.Failure(throwable)
                }
              }
            }
          }
        }

        is UnknownHostException -> stateException("No Internet")
        is ConnectException -> stateException("No Internet")
        else -> stateException("No Internet")
      }
    }
  }
}