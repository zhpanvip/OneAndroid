package com.zhpan.oneandroid.ui.login

import androidx.lifecycle.MutableLiveData
import com.zhpan.library.base.IActivityHost
import com.zhpan.library.server.common.ResponseObserver
import com.zhpan.oneandroid.base.BaseRepository
import com.zhpan.oneandroid.model.bean.User
import com.zhpan.oneandroid.utils.RxUtils


/**
 * <pre>
 *   Created by zhpan on 2020/7/30.
 *   Description:
 * </pre>
 */
class LoginRepository : BaseRepository() {
    fun login(
        iActivityHost: IActivityHost,
        showLoading: Boolean,
        username: String,
        password: String
    ): MutableLiveData<User> {
        val mutableLiveData = MutableLiveData<User>()
        getApiService().login(username, password)
            .compose(RxUtils.rxSchedulerHelper(iActivityHost, showLoading))
            .subscribe(object : ResponseObserver<User>() {
                override fun onSuccess(response: User?) {
                    mutableLiveData.value = response
                }

                override fun onFail(errorCode: Int, message: String?) {
                    super.onFail(errorCode, message)
                    mutableLiveData.value = null
                }
            })
        return mutableLiveData
    }
}