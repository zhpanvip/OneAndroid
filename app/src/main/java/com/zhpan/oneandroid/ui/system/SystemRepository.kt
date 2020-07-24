package com.zhpan.oneandroid.ui.system

import androidx.lifecycle.MutableLiveData
import com.zhpan.library.base.IFragmentHost
import com.zhpan.library.server.common.ResponseObserver
import com.zhpan.oneandroid.base.BaseRepository
import com.zhpan.oneandroid.model.bean.SystemItemBean
import com.zhpan.oneandroid.utils.RxUtils

/**
 *
 * @author zhangpan
 * @date 2020/7/24
 */
class SystemRepository : BaseRepository() {
    fun getSystemClassify(
        iFragment: IFragmentHost,
        showLoading: Boolean
    ): MutableLiveData<List<SystemItemBean>> {
        val liveData: MutableLiveData<List<SystemItemBean>> =
            MutableLiveData<List<SystemItemBean>>()
        getApiService().getSystemClassify()
            .compose(RxUtils.rxSchedulerHelper(iFragment, showLoading))
            .subscribe(object : ResponseObserver<List<SystemItemBean>>() {
                override fun onSuccess(response: List<SystemItemBean>?) {
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