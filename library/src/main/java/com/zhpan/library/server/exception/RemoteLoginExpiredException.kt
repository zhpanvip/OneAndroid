package com.zhpan.library.server.exception

import com.zhpan.library.server.common.ErrorCode.getErrorMessage

/**
 * Created by zhpan on 2020/7/8.
 * Description:
 */
class RemoteLoginExpiredException(val errorCode: Int, cause: String?) :
    RuntimeException(
        getErrorMessage(errorCode),
        Throwable(cause)
    ) 