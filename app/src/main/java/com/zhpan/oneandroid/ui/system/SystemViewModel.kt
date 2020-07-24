package com.zhpan.oneandroid.ui.system

import androidx.lifecycle.MutableLiveData
import com.zhpan.library.base.BaseViewModel
import com.zhpan.library.base.IFragmentHost
import com.zhpan.oneandroid.model.bean.SystemItemBean

/**
 *
 * @author zhangpan
 * @date 2020/7/24
 */
class SystemViewModel(private var repository: SystemRepository) : BaseViewModel() {
    fun getSystemClassify(
        iFragment: IFragmentHost,
        showLoading: Boolean
    ): MutableLiveData<List<SystemItemBean>> {
        return repository.getSystemClassify(iFragment, showLoading)
    }
}