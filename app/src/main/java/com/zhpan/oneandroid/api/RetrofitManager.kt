package com.zhpan.oneandroid.api

import com.zhpan.library.network.RetrofitCreator

object RetrofitManager {

//  private var mApiService: ApiService? = null
//  val apiService: ApiService
//    get() {
//      if (mApiService == null) {
//        mApiService =
//          com.zhpan.library.server.common.ApiServiceCreator.getApiService(
//            ApiService::class.java, ServerConfig.BASE_URL
//          )
//      }
//      return mApiService!!
//    }

  private var mApiService: ApiService? = null

  fun getApiService(): ApiService {
    if (mApiService == null) {
      mApiService =
        RetrofitCreator.createApiService(
          ApiService::class.java,
          ServerConfig.BASE_URL
        )
    }
    return mApiService!!
  }
}