package com.zhpan.oneandroid.app.login

import androidx.lifecycle.MutableLiveData
import com.zhpan.library.server.common.ResponseObserver
import com.zhpan.oneandroid.module.response.LoginResponse
import com.zhpan.oneandroid.repository.BaseRepository
import com.zhpan.oneandroid.utils.RxUtils


/**
 * <pre>
 *   Created by zhpan on 2020/7/11.
 *   Description:
 * </pre>
 */
class LoginRepository : BaseRepository() {
    fun login(username: String, password: String): MutableLiveData<LoginResponse> {
        val liveData: MutableLiveData<LoginResponse> = MutableLiveData()
        getApiService()?.login(username, password)?.compose(RxUtils.rxSchedulerHelper())
            ?.subscribe(object : ResponseObserver<LoginResponse?>() {
                override fun onSuccess(response: LoginResponse?) {
                    liveData.value = response;
                }
            })
        return liveData
    }
}