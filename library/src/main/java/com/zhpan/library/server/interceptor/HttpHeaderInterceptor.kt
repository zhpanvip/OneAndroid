package com.zhpan.library.server.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

/**
 * Created by zhpan on 2020/7/8.
 * Description:
 */
class HttpHeaderInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        //  配置请求头
        val accessToken = "token"
        val tokenType = "tokenType"
        val request = chain.request().newBuilder()
            .header("app_key", "appId")
            .header("Authorization", "$tokenType $accessToken")
            .header("Content-Type", "application/json")
            .addHeader("Connection", "close")
            .addHeader("Accept-Encoding", "identity")
            .build()
        return chain.proceed(request)
    }
}