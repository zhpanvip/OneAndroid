package com.zhpan.oneandroid.ui.knowledge

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 *
 * @author zhangpan
 * @date 2020/7/24
 */
class KnowledgeViewModelFactory : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(KnowledgeViewModel::class.java)) {
            return KnowledgeViewModel(KnowledgeRepository()) as T
        }
        return super.create(modelClass)
    }

}