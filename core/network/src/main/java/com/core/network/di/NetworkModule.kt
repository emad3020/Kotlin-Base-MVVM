package com.core.network.di

import android.content.Context
import com.core.network.annotations.ContentTypeJson
import com.core.network.converters.RetrofitConverter
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.Dispatcher
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

  @Provides
  @Singleton
  @ContentTypeJson
  fun provideJsonContentType(): String = "application/json"

  @Provides
  @Singleton
  fun provideHttpClient(
    httpLoggingInterceptor: HttpLoggingInterceptor
  ): OkHttpClient {
    val builder = OkHttpClient.Builder()
    builder.dispatcher(
      Dispatcher().apply {
        maxRequestsPerHost = 20
      }
    )
    builder.addInterceptor(httpLoggingInterceptor)
    builder.connectTimeout(120, TimeUnit.SECONDS)
    builder.readTimeout(120, TimeUnit.SECONDS)
    builder.writeTimeout(120, TimeUnit.SECONDS)
    return builder.build()
  }

  @Provides
  @Singleton
  fun provideLoggerInterceptor(): HttpLoggingInterceptor {
    val logging = HttpLoggingInterceptor()
    logging.level = HttpLoggingInterceptor.Level.BODY
    return logging
  }

  @Provides
  @Singleton
  fun provideJson(): Json = Json {
    ignoreUnknownKeys = true
    encodeDefaults = true
  }

  @Provides
  @Singleton
  fun provideJsonConverterFactory(
    json: Json,
    @ContentTypeJson
    contentTypeJson: String
  ): Converter.Factory = json.asConverterFactory(contentTypeJson.toMediaType())

  @Provides
  @Singleton
  fun provideRetrofitConverter(
    jsonConverterFactory: Converter.Factory
  ): RetrofitConverter = RetrofitConverter(jsonConverterFactory)

  @Provides
  @Singleton
  fun provideRetrofit(
    okHttpClient: OkHttpClient,
    retrofitConverter: RetrofitConverter
  ): Retrofit = Retrofit.Builder().baseUrl("http://url.to.server/api/")
    .addConverterFactory(retrofitConverter)
    .client(okHttpClient)
    .build()
}