package com.zhpan.oneandroid.ui.home

import androidx.lifecycle.MutableLiveData
import com.zhpan.library.base.BaseViewModel
import com.zhpan.library.base.IFragmentHost
import com.zhpan.oneandroid.model.response.ArticleResponse
import com.zhpan.oneandroid.model.bean.BannerBean


/**
 * <pre>
 *   Created by zhpan on 2020/7/11.
 *   Description:
 * </pre>
 */
class HomeViewModel(private val homeRepository: HomeRepository) : BaseViewModel() {

    fun getHomeArticles(
        fragmentHost: IFragmentHost,
        page: Int,
        showLoading: Boolean
    ): MutableLiveData<ArticleResponse> {
        return homeRepository.getHomeArticles(page, fragmentHost, showLoading)
    }

    fun getBannerData(fragmentHost: IFragmentHost): MutableLiveData<List<BannerBean>> {
        return homeRepository.getBannerData(fragmentHost)
    }
}