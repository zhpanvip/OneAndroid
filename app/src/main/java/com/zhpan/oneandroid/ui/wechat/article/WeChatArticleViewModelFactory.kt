package com.zhpan.oneandroid.ui.wechat.article

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

/**
 *
 * @author zhangpan
 * @date 2020/7/23
 */
class WeChatArticleViewModelFactory(private val repository: WeChatArticleRepository) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(WeChatArticleViewModel::class.java)){
            return WeChatArticleViewModel(
                repository
            ) as T
        }
        throw IllegalArgumentException("Error ViewModel Type")
    }
}