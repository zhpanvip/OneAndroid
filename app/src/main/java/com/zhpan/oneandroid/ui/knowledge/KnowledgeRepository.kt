package com.zhpan.oneandroid.ui.knowledge

import androidx.lifecycle.MutableLiveData
import com.zhpan.library.base.IFragmentHost
import com.zhpan.library.server.common.ResponseObserver
import com.zhpan.oneandroid.base.BaseAppRepository
import com.zhpan.oneandroid.model.bean.KnowledgeBean
import com.zhpan.oneandroid.utils.RxUtils

/**
 *
 * @author zhangpan
 * @date 2020/7/24
 */
class KnowledgeRepository : BaseAppRepository() {
    fun getSystemClassify(
        iFragment: IFragmentHost,
        showLoading: Boolean
    ): MutableLiveData<List<KnowledgeBean>> {
        val liveData: MutableLiveData<List<KnowledgeBean>> =
            MutableLiveData<List<KnowledgeBean>>()
        getApiService().getSystemClassify()
            .compose(RxUtils.rxSchedulerHelper(iFragment, showLoading))
            .subscribe(object : ResponseObserver<List<KnowledgeBean>>() {
                override fun onSuccess(response: List<KnowledgeBean>?) {
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