package com.zhpan.library.server.exception

import com.zhpan.library.server.common.ErrorCode.getErrorMessage
/**
 * Created by zhpan on 2020/7/8.
 * Description:服务器返回的异常
 */
class ServerResponseException(var errorCode: Int, cause: String?) :
    RuntimeException(
        getErrorMessage(
            errorCode,
            cause!!
        ), Throwable(cause)
    )