package com.zhpan.oneandroid.model.response

import com.zhpan.library.server.common.BasicResponse

/**
 * Created by zhpan on 2017/10/25.
 * Description:
 */
class LoginResponse : BasicResponse<Any?>() {
    var token: String? = null
    var refresh_token: String? = null
    var expired: String? = null
    var refresh_secret: String? = null

    /**
     * accessToken : *******
     * tokenType : *******
     * expiresIn : *******
     * refreshToken : *******
     * scope : *******
     */
    var accessToken: String? = null
    var tokenType: String? = null
    var expiresIn: String? = null
    var refreshToken: String? = null
    var scope: String? = null

}