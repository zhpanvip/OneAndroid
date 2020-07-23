package com.zhpan.oneandroid.ui.project

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


/**
 * <pre>
 *   Created by zhpan on 2020/7/23.
 *   Description:
 * </pre>
 */
class ProjectViewModelFactory(private var repository: ProjectRepository) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProjectViewModel::class.java)) {
            return ProjectViewModel(repository) as T
        }
        return super.create(modelClass)
    }
}