package com.zhpan.oneandroid.ui.wechat

import androidx.lifecycle.MutableLiveData
import com.zhpan.library.base.IFragmentHost
import com.zhpan.library.server.common.ResponseObserver
import com.zhpan.oneandroid.base.BaseRepository
import com.zhpan.oneandroid.model.bean.OfficialAccountBean
import com.zhpan.oneandroid.utils.RxUtils

/**
 *
 * @author zhangpan
 * @date 2020/7/22
 */
class OfficialAccountRepository : BaseRepository() {
    fun getOfficialAccounts(
        iPageHost: IFragmentHost,
        showLoading: Boolean
    ): MutableLiveData<List<OfficialAccountBean>> {
        val mutableLiveData = MutableLiveData<List<OfficialAccountBean>>()
        getApiService().getOfficialAccounts()
            .compose(RxUtils.rxSchedulerHelper(iPageHost, showLoading))
            .subscribe(object : ResponseObserver<List<OfficialAccountBean>>() {
                override fun onSuccess(response: List<OfficialAccountBean>?) {
                    mutableLiveData.value = response
                }

                override fun onFail(errorCode: Int, message: String?) {
                    super.onFail(errorCode, message)
                    mutableLiveData.value = null
                }
            })

        return mutableLiveData
    }
}