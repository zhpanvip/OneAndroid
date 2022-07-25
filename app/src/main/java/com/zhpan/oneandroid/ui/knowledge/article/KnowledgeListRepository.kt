package com.zhpan.oneandroid.ui.knowledge.article

import com.zhpan.library.base.BaseRepository
import com.zhpan.library.network.ResponseMutableLiveData
import com.zhpan.oneandroid.api.RetrofitManager
import com.zhpan.oneandroid.model.response.ArticleResponse

/**
 * <pre>
 *   Created by zhpan on 2020/7/25.
 *   Description:
 * </pre>
 */
class KnowledgeListRepository : BaseRepository() {

  suspend fun getKnowledgeArticles(
    showLoading: Boolean,
    page: Int,
    cid: String,
    responseLiveData: ResponseMutableLiveData<ArticleResponse>
  ) {
    executeRequest({
      RetrofitManager.getApiService().getKnowledgeArticles(page, cid)
    }, responseLiveData,showLoading)
  }
}