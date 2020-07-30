package com.zhpan.oneandroid.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


/**
 * <pre>
 *   Created by zhpan on 2020/7/30.
 *   Description:
 * </pre>
 */
class LoginViewModelFactory(private val loginRepository: LoginRepository) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(loginRepository) as T
        }
        return super.create(modelClass)
    }
}