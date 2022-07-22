package com.zhpan.oneandroid.ui.home

import androidx.lifecycle.viewModelScope
import com.zhpan.library.base.BaseViewModel
import com.zhpan.library.network.ResponseLiveData
import com.zhpan.library.network.ResponseMutableLiveData
import com.zhpan.oneandroid.model.response.ArticleResponse
import com.zhpan.oneandroid.model.bean.BannerBean
import kotlinx.coroutines.launch

/**
 * <pre>
 *   Created by zhpan on 2020/7/11.
 *   Description:
 * </pre>
 */
class HomeViewModel : BaseViewModel<HomeRepository>() {

  private var _bannerLiveData = ResponseMutableLiveData<List<BannerBean>>()
  var bannerLiveData: ResponseLiveData<List<BannerBean>> = _bannerLiveData
  private var _articleLiveData = ResponseMutableLiveData<ArticleResponse>()
  var articleLiveData: ResponseLiveData<ArticleResponse> = _articleLiveData

  fun getHomeArticles(page: Int, showLoading: Boolean) {
    viewModelScope.launch {
      repository.getHomeArticles(page, _articleLiveData, showLoading)
    }
  }

  fun getBannerData() {
    viewModelScope.launch {
      repository.getBannerData(_bannerLiveData)
    }
  }

  override fun createRepository(): HomeRepository {
    return HomeRepository()
  }
}