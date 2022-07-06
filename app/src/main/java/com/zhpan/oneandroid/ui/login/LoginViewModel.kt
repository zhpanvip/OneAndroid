package com.zhpan.oneandroid.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.zhpan.library.base.BaseViewModel
import com.zhpan.library.base.IActivityHost
import com.zhpan.library.network.StateLiveData
import com.zhpan.oneandroid.api.RetrofitCreator
import com.zhpan.oneandroid.api.RetrofitCreator2
import com.zhpan.oneandroid.model.bean.User
import kotlinx.coroutines.launch

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

  fun login(userName: String, password: String) {
    viewModelScope.launch {
      request({ RetrofitCreator2.getLoginAPI().login(userName, password) }, loginLiveData)
    }
  }

  var loginLiveData = StateLiveData<User>()
}