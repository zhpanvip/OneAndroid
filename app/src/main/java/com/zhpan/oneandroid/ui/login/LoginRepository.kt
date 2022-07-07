package com.zhpan.oneandroid.ui.login

import com.zhpan.library.network.StateLiveData
import com.zhpan.oneandroid.api.RetrofitCreator2
import com.zhpan.oneandroid.base.BaseAppRepository
import com.zhpan.oneandroid.model.bean.User

/**
 * <pre>
 *   Created by zhpan on 2020/7/30.
 *   Description:
 * </pre>
 */
class LoginRepository : BaseAppRepository() {
  suspend fun login(userName: String, password: String, loginLiveData: StateLiveData<User>) {
    executeRequest({
      RetrofitCreator2.getLoginAPI().login(userName, password)
    }, loginLiveData)
  }
}