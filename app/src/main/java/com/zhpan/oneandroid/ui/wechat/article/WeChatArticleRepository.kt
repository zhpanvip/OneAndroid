package com.zhpan.oneandroid.ui.wechat.article

import com.zhpan.library.base.BaseRepository
import com.zhpan.library.network.ResponseMutableLiveData
import com.zhpan.oneandroid.api.RetrofitManager
import com.zhpan.oneandroid.model.response.ArticleResponse

/**
 *
 * @author zhangpan
 * @date 2020/7/23
 */
class WeChatArticleRepository : BaseRepository() {
  suspend fun getWeChatArticles(
    responseLiveData: ResponseMutableLiveData<ArticleResponse>,
    accountId: String?,
    page: Int,
    showLoading: Boolean
  ) {
    executeRequest({
      RetrofitManager.getApiService().getWechatArticles(accountId, page)
    }, responseLiveData, showLoading)
  }
}