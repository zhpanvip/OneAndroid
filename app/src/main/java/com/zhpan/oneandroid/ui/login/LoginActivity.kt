package com.zhpan.oneandroid.ui.login

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.blankj.utilcode.util.ToastUtils
import com.zhpan.library.base.BaseActivity
import com.zhpan.library.base.BaseViewModel
import com.zhpan.library.utils.InputTextHelper
import com.zhpan.oneandroid.R
import com.zhpan.oneandroid.databinding.ActivityLoginBinding
import com.zhpan.oneandroid.model.response.LoginResponse
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
        btn_login.setOnClickListener {
            mViewModel?.login(
                this@LoginActivity,
                et_username.text.toString(),
                et_password.text.toString()
            )?.observe(this@LoginActivity,
                Observer<LoginResponse> { t ->
                    if (t != null) {
                        ToastUtils.showShort("登录成功！")
                        finish()
                    }
                })
        }
    }

    override fun getLayoutId(): Int = R.layout.activity_login
}