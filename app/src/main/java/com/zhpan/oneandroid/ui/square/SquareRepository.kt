package com.zhpan.oneandroid.ui.square

import androidx.lifecycle.MutableLiveData
import com.zhpan.library.base.IFragmentHost
import com.zhpan.library.server.common.ResponseObserver
import com.zhpan.oneandroid.base.BaseAppRepository
import com.zhpan.oneandroid.model.response.ArticleResponse
import com.zhpan.oneandroid.utils.RxUtils


/**
 * <pre>
 *   Created by zhpan on 2020/7/18.
 *   Description:
 * </pre>
 */
class SquareRepository() : BaseAppRepository() {
    fun getSquareArticles(
        page: Int,
        host: IFragmentHost,
        showLoading: Boolean
    ): MutableLiveData<ArticleResponse> {
        val liveData: MutableLiveData<ArticleResponse> = MutableLiveData()
        getApiService().getSquareArticles(page)
            .compose(RxUtils.rxSchedulerHelper(host, showLoading))
            .subscribe(object : ResponseObserver<ArticleResponse>() {
                override fun onSuccess(response: ArticleResponse?) {
                    liveData.value = response
                }

                override fun onFail(errorCode: Int, message: String?) {
                    super.onFail(errorCode, message)
                    val articleResponse = ArticleResponse()
                    articleResponse.success = false
                    articleResponse.errorCode = errorCode
                    liveData.value = articleResponse
                }
            })

        return liveData
    }
}