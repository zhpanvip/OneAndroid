package com.zhpan.oneandroid.model.response


/**
 * <pre>
 *   Created by zhpan on 2020/7/18.
 *   Description:
 * </pre>
 */
open class BaseResponse {
    var success: Boolean? = true
    var errorCode: Int? = 0
    var errorMsg: String? = ""
}