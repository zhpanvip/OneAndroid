package com.zhpan.oneandroid.utils

import android.content.Context
import com.blankj.utilcode.util.Utils
import com.zhpan.library.server.interceptor.CookiesInterceptor
import com.zhpan.oneandroid.R
import com.zhpan.oneandroid.model.bean.User


/**
 *
 * @author zhangpan
 * @date 2020/12/11
 */
object UserInfoHelper {
    private var user: User? = null
    fun init(context: Context) {
        user = getUser(context)
        if (user == null) {
            user = User()
        }
        setUserInfoBean(context, user)
    }

    /**
     * 设置本地个人信息
     */
    fun setUserInfoBean(context: Context, user: User?) {
        this.user = user
        SharedPreferencesHelper.saveObject(context, this.user)
    }

    fun logout() {
        CookiesInterceptor.clearCookie(Utils.getApp())
        SharedPreferencesHelper.clear(Utils.getApp())
        user = null
    }

    private fun getUser(context: Context): User? {
        if (user == null) {
            user = SharedPreferencesHelper.getObject(
                context.applicationContext,
                User::class.java
            )
        }
        if (user == null) {
            user = User()
        }
        return user
    }

    fun isLogin(): Boolean {
        return user?.isLogin()!!
    }

    fun getUserAvatarUrl(): String? {
        return user?.icon
    }


    fun getUserName(): String? {
        if (isLogin()) {
            return user?.username
        }
        return Utils.getApp().resources.getString(R.string.go_to_login)
    }
}
