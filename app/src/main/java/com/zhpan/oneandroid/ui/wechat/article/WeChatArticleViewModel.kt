package com.zhpan.oneandroid.ui.wechat.article

import androidx.lifecycle.MutableLiveData
import com.zhpan.library.base.BaseViewModel
import com.zhpan.library.base.IFragmentHost
import com.zhpan.oneandroid.model.response.ArticleResponse
import com.zhpan.oneandroid.ui.wechat.article.WeChatArticleRepository

/**
 *
 * @author zhangpan
 * @date 2020/7/23
 */
open class WeChatArticleViewModel(var weChatArticleRepository: WeChatArticleRepository) :
    BaseViewModel() {
    fun getWeChatViewModel(
        iFragmentHost: IFragmentHost,
        accountId: String?,
        page: Int,
        showLoading: Boolean
    ): MutableLiveData<ArticleResponse> {
        return weChatArticleRepository.getWeChatArticles(
            iFragmentHost,
            accountId,
            page,
            showLoading
        )
    }
}