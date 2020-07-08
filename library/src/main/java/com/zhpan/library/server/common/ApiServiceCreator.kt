package com.zhpan.library.server.common

/**
 * Created by zhpan on 2020/7/8.
 * Description:
 */
object ApiServiceCreator {
    @JvmStatic
    fun <T> getApiService(cls: Class<T>?, baseUrl: String?): T {
        val retrofit = RetrofitManager.getRetrofitBuilder(
            baseUrl
        ).build()
        return retrofit.create(cls)
    }
}