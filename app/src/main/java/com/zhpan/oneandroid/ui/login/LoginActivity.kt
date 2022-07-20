package com.zhpan.oneandroid.ui.login

import android.os.Bundle
import com.blankj.utilcode.util.ToastUtils
import com.zhpan.library.base.BaseActivity
import com.zhpan.library.network.ResponseObserver
import com.zhpan.library.utils.InputTextHelper
import com.zhpan.oneandroid.R
import com.zhpan.oneandroid.databinding.ActivityLoginBinding
import com.zhpan.oneandroid.model.bean.User
import com.zhpan.oneandroid.utils.UserInfoHelper
import kotlinx.android.synthetic.main.activity_login.*

/**
 * @author zhangpan
 */
class LoginActivity(override var layoutId: Int = R.layout.activity_login) :
  BaseActivity<LoginViewModel, ActivityLoginBinding>() {

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    setTransparentStatusBar()
    setStatusBarDarkMode(true)
    InputTextHelper.with(this)
      .setMain(btn_login)
      .addView(et_password)
      .addView(et_username)
      .build()

    mViewModel.loginLiveData.observe(this, object : ResponseObserver<User>() {
      override fun onSuccess(data: User?) {
        if (data != null) {
          ToastUtils.showShort("登录成功！")
          UserInfoHelper.setUserInfoBean(this@LoginActivity, data)
          finish()
        }
      }
    })

    btn_login.setOnClickListener {
      mViewModel.login(
        mBinding.etUsername.text.toString().trim(),
        mBinding.etPassword.text.toString().trim()
      )
    }
  }
}