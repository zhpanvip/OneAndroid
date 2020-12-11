package com.zhpan.library.server.common

import com.blankj.utilcode.util.Utils
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.zhpan.library.server.converter.GsonConverterFactory
import com.zhpan.library.server.interceptor.*
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.io.File
import java.util.concurrent.TimeUnit

/**
 * Created by zhpan on 2020/7/8.
 * Description:
 */
object RetrofitManager {
    val okHttpClientBuilder: OkHttpClient.Builder
        get() {
            val cacheFile =
                File(Utils.getApp().cacheDir, "cache")
            val cache = Cache(cacheFile, 1024 * 1024 * 100) //100Mb
            return OkHttpClient.Builder()
                .readTimeout(
                    Config.DEFAULT_TIMEOUT.toLong(),
                    TimeUnit.MILLISECONDS
                )
                .connectTimeout(
                    Config.DEFAULT_TIMEOUT.toLong(),
                    TimeUnit.MILLISECONDS
                )
                .addInterceptor(LoggingInterceptor())
                .addInterceptor(CookiesInterceptor())
                .addInterceptor(AddCookiesInterceptor())
                .addInterceptor(HttpHeaderInterceptor())
                .addNetworkInterceptor(HttpCacheInterceptor())
                .cache(cache)
        }

    fun getRetrofitBuilder(baseUrl: String?): Retrofit.Builder {
        val gson =
            GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").serializeNulls().create()
        val okHttpClient = okHttpClientBuilder.build()
        return Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(baseUrl)
    }
}