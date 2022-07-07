package com.zhpan.oneandroid.ui.project

import androidx.lifecycle.MutableLiveData
import com.zhpan.library.base.IFragmentHost
import com.zhpan.library.server.common.ResponseObserver
import com.zhpan.oneandroid.base.BaseAppRepository
import com.zhpan.oneandroid.model.bean.ProjectClassify
import com.zhpan.oneandroid.utils.RxUtils


/**
 * <pre>
 *   Created by zhpan on 2020/7/23.
 *   Description:
 * </pre>
 */
class ProjectRepository : BaseAppRepository() {

    fun getProjectTrees(
        iFragment: IFragmentHost,
        showLoading: Boolean
    ): MutableLiveData<List<ProjectClassify>> {
        val mutableLiveData = MutableLiveData<List<ProjectClassify>>()
        getApiService().getProjectClassify().compose(RxUtils.rxSchedulerHelper(iFragment, showLoading))
            .subscribe(object : ResponseObserver<List<ProjectClassify>>() {
                override fun onSuccess(response: List<ProjectClassify>?) {
                    mutableLiveData.value = response
                }

                override fun onFail(errorCode: Int, message: String?) {
                    super.onFail(errorCode, message)
                    mutableLiveData.value = null
                }

            })
        return mutableLiveData;
    }
}