package com.zhpan.oneandroid.ui.home

import androidx.lifecycle.MutableLiveData
import com.zhpan.library.base.IFragmentHost
import com.zhpan.library.server.common.ResponseObserver
import com.zhpan.oneandroid.model.response.ArticleResponse
import com.zhpan.oneandroid.model.bean.BannerBean
import com.zhpan.oneandroid.base.BaseRepository
import com.zhpan.oneandroid.utils.RxUtils

/**
 * <pre>
 *   Created by zhpan on 2020/7/11.
 *   Description:
 * </pre>
 */
class HomeRepository : BaseRepository() {
    fun getHomeArticles(
        page: Int,
        fragmentHost: IFragmentHost,
        showLoading: Boolean
    ): MutableLiveData<ArticleResponse> {
        val liveData: MutableLiveData<ArticleResponse> = MutableLiveData()
        getApiService().getHomeArticles(page)
            .compose(RxUtils.rxSchedulerHelper(fragmentHost, showLoading))
            ?.subscribe(object : ResponseObserver<ArticleResponse>() {

                override fun onSuccess(response: ArticleResponse?) {
                    liveData.value = response
                }

                override fun onFail(errorCode: Int, message: String?) {
                    super.onFail(errorCode, message)
                    liveData.value = null
                }

            })
        return liveData
    }

    fun getBannerData(
        fragmentHost: IFragmentHost
    ): MutableLiveData<List<BannerBean>> {
        val liveData: MutableLiveData<List<BannerBean>> = MutableLiveData()
        getApiService().getBannerData()
            .compose(RxUtils.rxSchedulerHelper(fragmentHost, false))
            ?.subscribe(object : ResponseObserver<List<BannerBean>>() {

                override fun onSuccess(response: List<BannerBean>?) {
                    liveData.value = response
                }

                override fun onFail(errorCode: Int, message: String?) {
                    super.onFail(errorCode, message)
                    liveData.value = null
                }
            })
        return liveData
    }
}