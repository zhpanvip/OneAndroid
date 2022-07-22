package com.zhpan.oneandroid.ui.knowledge.article

import androidx.lifecycle.viewModelScope
import com.zhpan.library.base.BaseViewModel
import com.zhpan.library.network.ResponseLiveData
import com.zhpan.library.network.ResponseMutableLiveData
import com.zhpan.oneandroid.model.response.ArticleResponse
import kotlinx.coroutines.launch

/**
 * <pre>
 *   Created by zhpan on 2020/7/25.
 *   Description:
 * </pre>
 */
class KnowledgeListViewModel() : BaseViewModel<KnowledgeListRepository>() {
  private val _articleLiveData = ResponseMutableLiveData<ArticleResponse>()
  val articleLiveData: ResponseLiveData<ArticleResponse> = _articleLiveData

  fun getKnowledgeArticles(
    boolean: Boolean,
    page: Int,
    cid: String
  ) {
    viewModelScope.launch {
      repository.getKnowledgeArticles(boolean, page, cid, _articleLiveData)
    }
  }
}