package com.zhpan.oneandroid.ui.login

import androidx.lifecycle.viewModelScope
import com.zhpan.library.base.NewBaseViewModel
import com.zhpan.library.network.StateLiveData
import com.zhpan.oneandroid.model.bean.User
import kotlinx.coroutines.launch

/**
 * <pre>
 *   Created by zhpan on 2020/7/30.
 *   Description:
 * </pre>
 */
class LoginViewModel : NewBaseViewModel<LoginRepository>() {

  var loginLiveData = StateLiveData<User>()

  var loginBtnEnable: Boolean = false

  fun login(userName: String, password: String) {
    viewModelScope.launch {
      repository.login(userName, password, loginLiveData)
    }
  }

  override fun createRepository(): LoginRepository {
    return LoginRepository()
  }
}