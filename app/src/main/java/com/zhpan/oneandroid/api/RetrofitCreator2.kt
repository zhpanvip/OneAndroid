package com.zhpan.oneandroid.api

import com.zhpan.library.network.ApiServiceCreator

object RetrofitCreator2 {
  private var mApiService: LoginAPI? = null

  fun getLoginAPI(): LoginAPI {
    if (mApiService == null) {
      mApiService =
        ApiServiceCreator.getApiService(
          LoginAPI::class.java, ServerConfig.BASE_URL
        )
    }
    return mApiService!!
  }
}