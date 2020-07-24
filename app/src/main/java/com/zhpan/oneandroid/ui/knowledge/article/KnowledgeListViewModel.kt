package com.zhpan.oneandroid.ui.knowledge.article

import androidx.lifecycle.MutableLiveData
import com.zhpan.library.base.BaseViewModel
import com.zhpan.library.base.IFragmentHost
import com.zhpan.oneandroid.model.response.ArticleResponse


/**
 * <pre>
 *   Created by zhpan on 2020/7/25.
 *   Description:
 * </pre>
 */
class KnowledgeListViewModel(private var repository: KnowledgeListRepository) : BaseViewModel() {
    fun getKnowledgeArticles(
        iFragmentHost: IFragmentHost,
        boolean: Boolean,
        page: Int,
        cid: String
    ): MutableLiveData<ArticleResponse> {
        return repository.getKnowledgeArticles(iFragmentHost, boolean, page, cid)
    }
}