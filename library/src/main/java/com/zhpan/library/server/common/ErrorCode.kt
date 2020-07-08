package com.zhpan.library.server.common

import androidx.annotation.StringRes
import com.blankj.utilcode.util.Utils
import com.zhpan.library.R

/**
 * Created by zhpan on 2020/7/8.
 * Description:
 */
object ErrorCode {
    /**
     * request success
     */
    const val SUCCESS = 0
    const val REQUEST_FAILED = -1
    const val REMOTE_LOGIN = -10000

    /**
     * 登录状态失效
     */
    const val INVALID_LOGIN_STATUS = -1001

    @JvmStatic
    fun getErrorMessage(errorCode: Int): String {
        return getErrorMessage(
            errorCode,
            ""
        )
    }

    /**
     * get error message with error code
     *
     * @param errorCode error code
     * @return error message
     */
    @JvmStatic
    fun getErrorMessage(errorCode: Int, errorMsg: String): String {
        return when (errorCode) {
            REQUEST_FAILED -> getString(
                R.string.login_error
            ) + errorCode + ",Error Message:" + errorMsg
            else -> getString(R.string.request_error) + errorCode
        }
    }

    private fun getString(@StringRes resId: Int): String {
        return Utils.getApp().getString(resId)
    }
}