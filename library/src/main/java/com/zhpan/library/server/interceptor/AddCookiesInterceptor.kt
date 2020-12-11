package com.zhpan.library.server.interceptor

import android.content.Context
import android.text.TextUtils
import com.blankj.utilcode.util.Utils
import com.zhpan.library.server.interceptor.CookiesInterceptor.Companion.COOKIE_PREF
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

/**
 * @author zhangpan
 * @date 2020/12/11
 * @description 通过拦截器添向请求头中添加cookie
 */
class AddCookiesInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()
        val builder: Request.Builder = request.newBuilder()
        val cookie = getCookie(request.url.toString(), request.url.host)
        if (!TextUtils.isEmpty(cookie)) {
            builder.addHeader("Cookie", cookie!!)
        }
        return chain.proceed(builder.build())
    }

    private fun getCookie(url: String, domain: String): String? {
        val sp = Utils.getApp().getSharedPreferences(COOKIE_PREF, Context.MODE_PRIVATE)
        if (!TextUtils.isEmpty(url) && sp.contains(url) && !TextUtils.isEmpty(
                sp.getString(
                    url,
                    ""
                )
            )
        ) {
            return sp.getString(url, "")
        }
        return if (!TextUtils.isEmpty(domain) && sp.contains(domain) && !TextUtils.isEmpty(
                sp.getString(
                    domain,
                    ""
                )
            )
        ) {
            sp.getString(domain, "")
        } else null
    }
}