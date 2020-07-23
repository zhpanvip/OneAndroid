package com.zhpan.oneandroid.ui.project.list

import androidx.lifecycle.MutableLiveData
import com.zhpan.library.base.IFragmentHost
import com.zhpan.library.server.common.ResponseObserver
import com.zhpan.oneandroid.base.BaseRepository
import com.zhpan.oneandroid.model.bean.ProjectBean
import com.zhpan.oneandroid.model.response.ProjectResponse
import com.zhpan.oneandroid.utils.RxUtils


/**
 * <pre>
 *   Created by zhpan on 2020/7/24.
 *   Description:
 * </pre>
 */
class ProjectListRepository : BaseRepository() {
    fun getProjectList(
        iFragmentHost: IFragmentHost,
        showLoading: Boolean,
        page: Int,
        cid: String
    ): MutableLiveData<ProjectResponse> {
        var liveData = MutableLiveData<ProjectResponse>()
        getApiService().getProjectList(page, cid)
            .compose(RxUtils.rxSchedulerHelper(iFragmentHost, showLoading))
            .subscribe(object : ResponseObserver<ProjectResponse>() {
                override fun onSuccess(response: ProjectResponse?) {
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