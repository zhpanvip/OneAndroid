package com.zhpan.oneandroid.ui.wechat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 *
 * @author zhangpan
 * @date 2020/7/22
 */
class OfficialAccountViewModelFactory(private var repository: OfficialAccountRepository) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(OfficialAccountViewModel::class.java)) {
            return OfficialAccountViewModel(repository) as T
        }
        return super.create(modelClass)
    }
}