package com.zhpan.oneandroid.ui.square

import androidx.lifecycle.MutableLiveData
import com.zhpan.library.base.BaseViewModel
import com.zhpan.library.base.IFragmentHost
import com.zhpan.oneandroid.model.response.ArticleResponse


/**
 * <pre>
 *   Created by zhpan on 2020/7/18.
 *   Description:
 * </pre>
 */
class SquareViewModel(private var repository: SquareRepository) : BaseViewModel() {
    fun getSquareArticles(
        host: IFragmentHost,
        page: Int,
        showLoading: Boolean
    ): MutableLiveData<ArticleResponse> {

        return repository.getSquareArticles(page, host, showLoading)
    }
}