package com.zhpan.oneandroid.ui.knowledge.article

import androidx.lifecycle.MutableLiveData
import com.zhpan.library.base.IFragmentHost
import com.zhpan.library.server.common.ResponseObserver
import com.zhpan.oneandroid.base.BaseAppRepository
import com.zhpan.oneandroid.model.response.ArticleResponse
import com.zhpan.oneandroid.utils.RxUtils


/**
 * <pre>
 *   Created by zhpan on 2020/7/25.
 *   Description:
 * </pre>
 */
class KnowledgeListRepository : BaseAppRepository() {
    val liveData: MutableLiveData<ArticleResponse> = MutableLiveData()
    fun getKnowledgeArticles(
        iFragmentHost: IFragmentHost,
        boolean: Boolean,
        page: Int,
        cid: String
    ): MutableLiveData<ArticleResponse> {

        getApiService().getKnowledgeArticles(page, cid)
            .compose(RxUtils.rxSchedulerHelper(iFragmentHost, showLoading = boolean))
            .subscribe(object : ResponseObserver<ArticleResponse>() {
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
}