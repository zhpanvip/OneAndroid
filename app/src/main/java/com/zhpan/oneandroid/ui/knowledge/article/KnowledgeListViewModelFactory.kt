package com.zhpan.oneandroid.ui.knowledge.article

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


/**
 * <pre>
 *   Created by zhpan on 2020/7/25.
 *   Description:
 * </pre>
 */
class KnowledgeListViewModelFactory : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(KnowledgeListViewModel::class.java)) {
            return KnowledgeListViewModel(KnowledgeListRepository()) as T
        }
        return super.create(modelClass)
    }

}