package com.zhpan.oneandroid.app.home

import androidx.lifecycle.MutableLiveData
import com.zhpan.library.base.BaseViewModel
import com.zhpan.library.base.IFragmentHost
import com.zhpan.oneandroid.module.request.ArticleWrapper


/**
 * <pre>
 *   Created by zhpan on 2020/7/11.
 *   Description:
 * </pre>
 */
class HomeViewModel(private val homeRepository: HomeRepository) : BaseViewModel() {

    fun getHomeArticles(
        fragmentHost: IFragmentHost,
        showLoading: Boolean, isRefresh: Boolean
    ): MutableLiveData<ArticleWrapper> {
        if (isRefresh) {
            page = 0
        } else {
            ++page
        }
        return homeRepository.getHomeArticles(page, fragmentHost, showLoading)
    }
}