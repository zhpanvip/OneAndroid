package com.zhpan.oneandroid.ui.project.list

import androidx.lifecycle.MutableLiveData
import com.zhpan.library.base.BaseViewModel
import com.zhpan.library.base.IFragmentHost
import com.zhpan.oneandroid.model.bean.ProjectBean
import com.zhpan.oneandroid.model.response.ProjectResponse


/**
 * <pre>
 *   Created by zhpan on 2020/7/24.
 *   Description:
 * </pre>
 */
class ProjectListViewModel(private var repository: ProjectListRepository) : BaseViewModel() {
    fun getProjectList(
        iFragment: IFragmentHost,
        showLoading: Boolean,
        page: Int,
        cid: String
    ): MutableLiveData<ProjectResponse> {
        return repository.getProjectList(iFragment, showLoading, page, cid)
    }
}