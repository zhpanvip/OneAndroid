package com.zhpan.oneandroid.ui.square

import androidx.lifecycle.viewModelScope
import com.zhpan.library.base.BaseViewModel
import com.zhpan.library.network.ResponseLiveData
import com.zhpan.library.network.ResponseMutableLiveData
import com.zhpan.oneandroid.model.response.ArticleResponse
import kotlinx.coroutines.launch

/**
 * <pre>
 *   Created by zhpan on 2020/7/18.
 *   Description:
 * </pre>
 */
class SquareViewModel() : BaseViewModel<SquareRepository>() {
  private val _responseLiveData = ResponseMutableLiveData<ArticleResponse>()
  val responseLiveData: ResponseLiveData<ArticleResponse> = _responseLiveData

  fun getSquareArticles(
    page: Int,
    showLoading: Boolean
  ) {
    viewModelScope.launch {
      repository.getSquareArticles(_responseLiveData, page, showLoading)
    }
  }
}