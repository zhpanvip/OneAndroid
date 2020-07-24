package com.zhpan.oneandroid.ui.project

import androidx.lifecycle.MutableLiveData
import com.zhpan.library.base.BaseViewModel
import com.zhpan.library.base.IFragmentHost
import com.zhpan.oneandroid.model.bean.ProjectClassify


/**
 * <pre>
 *   Created by zhpan on 2020/7/23.
 *   Description:
 * </pre>
 */
class ProjectViewModel(private var projectRepository: ProjectRepository) : BaseViewModel() {
    fun getProjectTrees(
        iFragmentHost: IFragmentHost,
        showLoading: Boolean
    ): MutableLiveData<List<ProjectClassify>> {
        return projectRepository.getProjectTrees(iFragmentHost, showLoading)
    }
}