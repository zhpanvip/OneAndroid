package com.zhpan.library.server.interceptor

import android.content.Context
import android.text.TextUtils
import com.blankj.utilcode.util.Utils
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException
import java.util.*

/**
 * @author zhangpan
 * @date 2020/12/11
 * 通过拦截器保存Header中的cookie信息
 */
class CookiesInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()
        val response: Response = chain.proceed(request)
        //set-cookie可能为多个
        if (response.headers("set-cookie").isNotEmpty()) {
            val cookies = response.headers("set-cookie")
            val cookie = encodeCookie(cookies)
            saveCookie(request.url.toString(), request.url.host, cookie)
        }
        return response
    }

    /**
     * 整合cookie为唯一字符串
     */
    private fun encodeCookie(cookies: List<String>): String {
        val sb = StringBuilder()
        val set: MutableSet<String> = HashSet()
        for (cookie in cookies) {
            val arr = cookie.split(";".toRegex()).toTypedArray()
            for (s in arr) {
                if (set.contains(s)) {
                    continue
                }
                set.add(s)
            }
        }
        for (cookie in set) {
            sb.append(cookie).append(";")
        }
        val last = sb.lastIndexOf(";")
        if (sb.length - 1 == last) {
            sb.deleteCharAt(last)
        }
        return sb.toString()
    }

    /**
     * 保存cookie到本地，这里我们分别为该url和host设置相同的cookie，其中host可选
     * 这样能使得该cookie的应用范围更广
     */
    private fun saveCookie(url: String, domain: String, cookies: String) {
        val sp = Utils.getApp().getSharedPreferences(COOKIE_PREF, Context.MODE_PRIVATE)
        val editor = sp.edit()
        if (TextUtils.isEmpty(url)) {
            throw NullPointerException("url is null.")
        } else {
            editor.putString(url, cookies)
        }
        if (!TextUtils.isEmpty(domain)) {
            editor.putString(domain, cookies)
        }
        editor.apply()
    }

    companion object {
        const val COOKIE_PREF = "cookies_prefs"

        /**
         * 清除本地Cookie
         *
         * @param context Context
         */
        fun clearCookie(context: Context) {
            val sp = context.getSharedPreferences(COOKIE_PREF, Context.MODE_PRIVATE)
            sp.edit().clear().apply()
        }
    }
}