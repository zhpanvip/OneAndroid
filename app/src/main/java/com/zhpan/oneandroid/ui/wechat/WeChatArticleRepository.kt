package com.zhpan.oneandroid.ui.wechat

import androidx.lifecycle.MutableLiveData
import com.zhpan.library.base.IFragmentHost
import com.zhpan.library.server.common.ResponseObserver
import com.zhpan.oneandroid.base.BaseRepository
import com.zhpan.oneandroid.model.response.ArticleResponse
import com.zhpan.oneandroid.utils.RxUtils

/**
 *
 * @author zhangpan
 * @date 2020/7/23
 */
class WeChatArticleRepository : BaseRepository() {
    fun getWeChatArticles(
        iFragmentHost: IFragmentHost,
        accountId: String?,
        page: Int,
        showLoading: Boolean
    ): MutableLiveData<ArticleResponse> {
        val liveData: MutableLiveData<ArticleResponse> = MutableLiveData()
        getApiService().getWechatArticles(accountId, page)
            .compose(RxUtils.rxSchedulerHelper(iFragmentHost, showLoading))
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