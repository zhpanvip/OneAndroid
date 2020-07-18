package com.zhpan.oneandroid.model.response

import androidx.lifecycle.Observer

/**
 * <pre>
 * Created by zhpan on 2020/7/18.
 * Description:
</pre> *
 */
internal class Test<T : BaseResponse?> : Observer<T> {
    override fun onChanged(t: T) {}
}