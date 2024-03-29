package com.zhpan.oneandroid.ui.home

import com.zhpan.library.base.BaseRepository
import com.zhpan.library.network.ResponseMutableLiveData
import com.zhpan.oneandroid.api.RetrofitManager
import com.zhpan.oneandroid.model.response.ArticleResponse
import com.zhpan.oneandroid.model.bean.BannerBean

/**
 * <pre>
 *   Created by zhpan on 2020/7/11.
 *   Description:
 * </pre>
 */
class HomeRepository : BaseRepository() {

  suspend fun getHomeArticles(
    page: Int,
    responseLiveData: ResponseMutableLiveData<ArticleResponse>,
    showLoading:Boolean
  ) {
    executeRequest({
      RetrofitManager.getApiService().getHomeArticles(page)
    }, responseLiveData,showLoading)
  }

  suspend fun getBannerData(bannerLiveData: ResponseMutableLiveData<List<BannerBean>>) {
    executeRequest({
      RetrofitManager.getApiService().getBannerData()
    }, bannerLiveData)
  }
}