package com.zhpan.oneandroid.api

import com.zhpan.library.server.common.ApiServiceCreator.getApiService

object RetrofitCreator {
    private var mApiService: ApiService? = null
    val apiService: ApiService?
        get() {
            if (mApiService == null) mApiService =
                getApiService(
                    ApiService::class.java, ServerConfig.BASE_URL
                )
            return mApiService
        }
}