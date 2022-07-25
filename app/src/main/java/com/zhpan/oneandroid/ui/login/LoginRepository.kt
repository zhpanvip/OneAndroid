package com.zhpan.oneandroid.ui.login

import com.zhpan.library.base.BaseRepository
import com.zhpan.library.network.ResponseMutableLiveData
import com.zhpan.oneandroid.api.RetrofitManager
import com.zhpan.oneandroid.model.bean.User

/**
 * <pre>
 *   Created by zhpan on 2020/7/30.
 *   Description:
 * </pre>
 */
class LoginRepository : BaseRepository() {
  suspend fun login(userName: String, password: String, loginLiveData: ResponseMutableLiveData<User>) {
    executeRequest({
      RetrofitManager.getApiService().login(userName, password)
    }, loginLiveData)
  }
}