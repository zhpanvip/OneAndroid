package com.zhpan.oneandroid.ui.home

import androidx.lifecycle.viewModelScope
import com.zhpan.library.base.NewBaseViewModel
import com.zhpan.library.network.StateLiveData
import com.zhpan.oneandroid.model.response.ArticleResponse
import com.zhpan.oneandroid.model.bean.BannerBean
import kotlinx.coroutines.launch

/**
 * <pre>
 *   Created by zhpan on 2020/7/11.
 *   Description:
 * </pre>
 */
class HomeViewModel() : NewBaseViewModel<HomeRepository>() {

  var bannerLiveData: StateLiveData<List<BannerBean>> = StateLiveData()
  var articleLiveData: StateLiveData<ArticleResponse> = StateLiveData()

  fun getHomeArticles(page: Int, showLoading: Boolean) {
    viewModelScope.launch {
      repository.getHomeArticles(page, articleLiveData, showLoading)
    }
  }

  fun getBannerData() {
    viewModelScope.launch {
      repository.getBannerData(bannerLiveData)
    }
  }

  override fun createRepository(): HomeRepository {
    return HomeRepository()
  }
}