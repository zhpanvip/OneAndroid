package com.zhpan.library.network

/**
 * Created by zhpan on 2020/7/8.
 * Description:
 */
object ApiServiceCreator {
  fun <T> getApiService(cls: Class<T>?, baseUrl: String): T {
    val retrofit = RetrofitManager.getRetrofitBuilder(
      baseUrl
    ).build()
    return retrofit.create(cls)
  }
}