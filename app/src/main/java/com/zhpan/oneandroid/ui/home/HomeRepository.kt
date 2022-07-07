package com.zhpan.oneandroid.ui.home

import com.zhpan.library.network.StateLiveData
import com.zhpan.oneandroid.api.RetrofitCreator2
import com.zhpan.oneandroid.model.response.ArticleResponse
import com.zhpan.oneandroid.model.bean.BannerBean
import com.zhpan.oneandroid.base.BaseAppRepository

/**
 * <pre>
 *   Created by zhpan on 2020/7/11.
 *   Description:
 * </pre>
 */
class HomeRepository : BaseAppRepository() {

  suspend fun getHomeArticles(
    page: Int,
    stateLiveData: StateLiveData<ArticleResponse>,
    showLoading:Boolean
  ) {
    executeRequest({
      RetrofitCreator2.getLoginAPI().getHomeArticles(page)
    }, stateLiveData,showLoading)
  }

  suspend fun getBannerData(bannerLiveData: StateLiveData<List<BannerBean>>) {
    executeRequest({
      RetrofitCreator2.getLoginAPI().getBannerData()
    }, bannerLiveData)
  }
}