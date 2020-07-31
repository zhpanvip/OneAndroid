package com.zhpan.oneandroid.ui.login

import androidx.lifecycle.MutableLiveData
import com.zhpan.library.base.BaseViewModel
import com.zhpan.library.base.IActivityHost
import com.zhpan.oneandroid.model.bean.User


/**
 * <pre>
 *   Created by zhpan on 2020/7/30.
 *   Description:
 * </pre>
 */
class LoginViewModel(var loginRepository: LoginRepository) : BaseViewModel() {
    var loginBtnEnable: Boolean = false
    fun login(
        iActivityHost: IActivityHost,
        username: String,
        password: String
    ): MutableLiveData<User> {
        return loginRepository.login(iActivityHost, true, username, password)
    }
}