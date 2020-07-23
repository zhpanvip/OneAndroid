package com.zhpan.oneandroid.ui.project

import androidx.lifecycle.MutableLiveData
import com.zhpan.library.base.IFragmentHost
import com.zhpan.library.server.common.ResponseObserver
import com.zhpan.oneandroid.base.BaseRepository
import com.zhpan.oneandroid.model.bean.ProjectTree
import com.zhpan.oneandroid.utils.RxUtils


/**
 * <pre>
 *   Created by zhpan on 2020/7/23.
 *   Description:
 * </pre>
 */
class ProjectRepository : BaseRepository() {

    fun getProjectTrees(
        iFragment: IFragmentHost,
        showLoading: Boolean
    ): MutableLiveData<List<ProjectTree>> {
        val mutableLiveData = MutableLiveData<List<ProjectTree>>()
        getApiService().getProjectTree().compose(RxUtils.rxSchedulerHelper(iFragment, showLoading))
            .subscribe(object : ResponseObserver<List<ProjectTree>>() {
                override fun onSuccess(response: List<ProjectTree>?) {
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