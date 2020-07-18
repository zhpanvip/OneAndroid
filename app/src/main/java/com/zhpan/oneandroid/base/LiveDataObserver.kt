package com.zhpan.oneandroid.base

import androidx.lifecycle.Observer
import com.zhpan.oneandroid.model.response.BaseResponse


/**
 * <pre>
 *   Created by zhpan on 2020/7/18.
 *   Description:
 * </pre>
 */
open class LiveDataObserver<T : BaseResponse> : Observer<T> {
    override fun onChanged(t: T) {
        if (t.success!!) {
            onSuccess(t)
        } else {
            onFail(t.errorCode, t.errorMsg)
        }
    }

    open fun onFail(errorCode: Int?, errorMsg: String?) {

    }

    open fun onSuccess(t: T) {

    }

}