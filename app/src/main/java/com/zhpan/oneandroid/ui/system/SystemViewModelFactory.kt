package com.zhpan.oneandroid.ui.system

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 *
 * @author zhangpan
 * @date 2020/7/24
 */
class SystemViewModelFactory : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SystemViewModel::class.java)) {
            return SystemViewModel(SystemRepository()) as T
        }
        return super.create(modelClass)
    }

}