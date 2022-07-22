package com.zhpan.oneandroid.ui.wechat.article

import androidx.lifecycle.viewModelScope
import com.zhpan.library.base.BaseViewModel
import com.zhpan.library.network.ResponseLiveData
import com.zhpan.library.network.ResponseMutableLiveData
import com.zhpan.oneandroid.model.response.ArticleResponse
import kotlinx.coroutines.launch

/**
 *
 * @author zhangpan
 * @date 2020/7/23
 */
open class WeChatArticleViewModel :
  BaseViewModel<WeChatArticleRepository>() {
  private val _responseLiveData = ResponseMutableLiveData<ArticleResponse>()
  val responseLiveData: ResponseLiveData<ArticleResponse> = _responseLiveData

  fun getWeChatViewModel(
    accountId: String?,
    page: Int,
    showLoading: Boolean
  ) {
    viewModelScope.launch {
      repository.getWeChatArticles(
        _responseLiveData,
        accountId,
        page,
        showLoading
      )
    }
  }
}