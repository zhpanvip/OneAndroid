package com.zhpan.oneandroid.app.home

import androidx.lifecycle.MutableLiveData
import com.zhpan.library.base.IFragmentHost
import com.zhpan.library.server.common.ResponseObserver
import com.zhpan.oneandroid.module.request.ArticleWrapper
import com.zhpan.oneandroid.repository.BaseRepository
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
    ): MutableLiveData<ArticleWrapper> {
        val liveData: MutableLiveData<ArticleWrapper> = MutableLiveData()
        getApiService()?.getHomeArticles(page)
            ?.compose(RxUtils.rxSchedulerHelper(fragmentHost, showLoading))
            ?.subscribe(object : ResponseObserver<ArticleWrapper>() {
                override fun onSuccess(response: ArticleWrapper?) {
                    liveData.value = response
                }

            })
        return liveData
    }
}