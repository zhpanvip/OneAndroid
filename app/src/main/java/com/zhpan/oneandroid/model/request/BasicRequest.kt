package com.zhpan.oneandroid.model.request

import com.blankj.utilcode.util.SPUtils

/**
 * Created by zhpan on 2017/10/25.
 * Description:
 */
open class BasicRequest {
    var token = SPUtils.getInstance().getString("token") as String

}