package com.zhpan.oneandroid.ui.square

import com.zhpan.library.base.BaseRepository
import com.zhpan.library.network.ResponseMutableLiveData
import com.zhpan.oneandroid.api.RetrofitCreator
import com.zhpan.oneandroid.model.response.ArticleResponse

/**
 * <pre>
 *   Created by zhpan on 2020/7/18.
 *   Description:
 * </pre>
 */
class SquareRepository() : BaseRepository() {
  suspend fun getSquareArticles(
    responseLiveData: ResponseMutableLiveData<ArticleResponse>,
    page: Int,
    showLoading: Boolean
  ) {
    executeRequest(
      { RetrofitCreator.getLoginAPI().getSquareArticles(page) },
      responseLiveData,
      showLoading
    )
  }
}