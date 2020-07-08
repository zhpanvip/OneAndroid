package com.zhpan.library.server.interceptor

import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.NetworkUtils
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

/**
 * Created by zhpan on 2020/7/8.
 * Description:
 */
class HttpCacheInterceptor : Interceptor {
    //  配置缓存的拦截器
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        if (!NetworkUtils.isConnected()) {  //没网强制从缓存读取
            request = request.newBuilder()
                .cacheControl(CacheControl.FORCE_CACHE)
                .build()
            LogUtils.d("Okhttp", "no network")
        }
        val originalResponse = chain.proceed(request)
        return if (NetworkUtils.isConnected()) {
            //有网的时候读接口上的@Headers里的配置，你可以在这里进行统一的设置
            val cacheControl = request.cacheControl.toString()
            originalResponse.newBuilder()
                .header("Cache-Control", cacheControl)
                .removeHeader("Pragma")
                .build()
        } else {
            originalResponse.newBuilder()
                .header("Cache-Control", "public, only-if-cached, max-stale=2419200")
                .removeHeader("Pragma")
                .build()
        }
    }
}