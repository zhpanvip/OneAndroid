package com.zhpan.oneandroid.ui.login

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.blankj.utilcode.util.ToastUtils
import com.zhpan.library.base.BaseActivity
import com.zhpan.library.network.ResponseObserver
import com.zhpan.library.network.StateLiveData
import com.zhpan.library.server.common.BasicResponse
import com.zhpan.library.utils.InputTextHelper
import com.zhpan.oneandroid.R
import com.zhpan.oneandroid.databinding.ActivityLoginBinding
import com.zhpan.oneandroid.model.bean.User
import com.zhpan.oneandroid.model.response.BaseResponse
import com.zhpan.oneandroid.utils.UserInfoHelper
import kotlinx.android.synthetic.main.activity_login.*

/**
 * @author zhangpan
 */
class LoginActivity : BaseActivity<LoginViewModel, ActivityLoginBinding>() {

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    setTransparentStatusBar()
    setStatusBarDarkMode(true)
    InputTextHelper.with(this)
      .setMain(btn_login)
      .addView(et_password)
      .addView(et_username)
      .build()
    mViewModel = ViewModelProvider(
      this,
      LoginViewModelFactory(LoginRepository())
    ).get(LoginViewModel::class.java)

    mViewModel?.loginLiveData?.observe(this, object : ResponseObserver<User>() {

      override fun onSuccess(data: User?) {
        if (data != null) {
          ToastUtils.showShort("登录成功！")
          UserInfoHelper.setUserInfoBean(this@LoginActivity, data)
          finish()
        }
      }

      override fun onFail(errorCode: Int, errorMsg: String?) {
        ToastUtils.showShort("登录失败！")
      }
    })

    btn_login.setOnClickListener {
      mViewModel?.login(
        et_username.text.toString().trim(),
        et_password.text.toString().trim()
      )
    }
  }

  override fun getLayoutId(): Int = R.layout.activity_login
}