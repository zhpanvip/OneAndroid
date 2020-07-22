package com.zhpan.oneandroid.ui.wechat

import androidx.lifecycle.MutableLiveData
import com.zhpan.library.base.BaseViewModel
import com.zhpan.library.base.IFragmentHost
import com.zhpan.oneandroid.model.bean.OfficialAccountBean

/**
 *
 * @author zhangpan
 * @date 2020/7/22
 */
class OfficialAccountViewModel(val officialAccountRepository: OfficialAccountRepository) :
    BaseViewModel() {
    fun getOificialAccountViewModel(
        iFragmentHost: IFragmentHost,
        showLoading: Boolean
    ): MutableLiveData<List<OfficialAccountBean>> {
        return officialAccountRepository.getOfficialAccounts(iFragmentHost, showLoading);
    }
}