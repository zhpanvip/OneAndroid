package com.zhpan.oneandroid.ui.login

import androidx.lifecycle.viewModelScope
import com.zhpan.library.base.BaseViewModel
import com.zhpan.library.network.ResponseLiveData
import com.zhpan.library.network.ResponseMutableLiveData
import com.zhpan.oneandroid.model.bean.User
import kotlinx.coroutines.launch

/**
 * <pre>
 *   Created by zhpan on 2020/7/30.
 *   Description:
 * </pre>
 */
class LoginViewModel : BaseViewModel<LoginRepository>() {

  private var _loginLiveData = ResponseMutableLiveData<User>()
  var loginLiveData: ResponseLiveData<User> = _loginLiveData

  var loginBtnEnable: Boolean = false

  fun login(userName: String, password: String) {
    viewModelScope.launch {
      repository.login(userName, password, _loginLiveData)
    }
  }

  override fun createRepository(): LoginRepository {
    return LoginRepository()
  }
}