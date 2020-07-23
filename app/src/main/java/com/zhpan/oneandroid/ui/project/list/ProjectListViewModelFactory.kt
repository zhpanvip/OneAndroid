package com.zhpan.oneandroid.ui.project.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


/**
 * <pre>
 *   Created by zhpan on 2020/7/24.
 *   Description:
 * </pre>
 */
class ProjectListViewModelFactory(private var repository: ProjectListRepository) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProjectListViewModel::class.java)) {
            return ProjectListViewModel(repository) as T
        }
        return super.create(modelClass)
    }
}