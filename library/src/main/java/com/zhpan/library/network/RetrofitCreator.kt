package com.zhpan.library.network

import android.util.Log
import com.blankj.utilcode.util.Utils
import com.zhpan.library.server.common.Config
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

private const val TAG = "RetrofitManager"

object RetrofitCreator {
  private val cache = Cache(File(Utils.getApp().cacheDir, "cache"), 1024 * 1024 * 100) //100Mb
  private val mOkClient = OkHttpClient.Builder()
    .callTimeout(
      Config.DEFAULT_TIMEOUT.toLong(),
      TimeUnit.MILLISECONDS
    )
    .connectTimeout(
      Config.DEFAULT_TIMEOUT.toLong(),
      TimeUnit.MILLISECONDS
    )
    .readTimeout(
      Config.DEFAULT_TIMEOUT.toLong(),
      TimeUnit.MILLISECONDS
    )
    .writeTimeout(
      Config.DEFAULT_TIMEOUT.toLong(),
      TimeUnit.MILLISECONDS
    )
    .retryOnConnectionFailure(true)
    .followRedirects(false)
    .cookieJar(LocalCookieJar())
    .addInterceptor(HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
      override fun log(message: String) {
        Log.d(TAG, "log: $message")
      }

    }).setLevel(HttpLoggingInterceptor.Level.BODY)).cache(cache).build()

  private fun getRetrofitBuilder(baseUrl: String): Retrofit.Builder {
    return Retrofit.Builder()
      .baseUrl(baseUrl)
      .client(mOkClient)
      .addConverterFactory(GsonConverterFactory.create())
  }

  fun <T> createApiService(cls: Class<T>, baseUrl: String): T {
    val retrofit = getRetrofitBuilder(
      baseUrl
    ).build()
    return retrofit.create(cls)
  }
}