package com.zhpan.library.server.common

/**
 * Created by zhpan on 2020/7/8.
 * Description:
 */
interface Config {
    companion object {
        /**
         * 网络请求超时时间毫秒
         */
        const val DEFAULT_TIMEOUT = 20000
    }
}