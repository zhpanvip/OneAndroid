package com.zhpan.library.server.exception

/**
 * Created by zhpan on 2020/7/8.
 * Description:服务器返回的data为null时抛出异常
 */
class NoDataExceptionException :
    RuntimeException("服务器没有返回对应的Data数据", Throwable("Server error"))