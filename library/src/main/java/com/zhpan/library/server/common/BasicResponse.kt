package com.zhpan.library.server.common

/**
 * Created by zhpan on 2020/7/8.
 * Description:
 */
open class BasicResponse<T> {
    var errorCode = 0
        private set
    var errorMsg: String? = null
    var data: T? = null
        private set

    fun setData(results: T) {
        data = results
    }

    fun setCode(code: Int) {
        errorCode = code
    }

}