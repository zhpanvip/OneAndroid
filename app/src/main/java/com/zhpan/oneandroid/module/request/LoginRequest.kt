package com.zhpan.oneandroid.module.request

/**
 * Created by zhpan on 2017/10/25.
 * Description:登录请求实体类
 */
class LoginRequest : BasicRequest() {
    var username: String? = null
    var password: String? = null
}