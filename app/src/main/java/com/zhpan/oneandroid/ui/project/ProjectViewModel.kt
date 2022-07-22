package com.zhpan.oneandroid.ui.project

import androidx.lifecycle.viewModelScope
import com.zhpan.library.base.BaseViewModel
import com.zhpan.library.network.ResponseMutableLiveData
import com.zhpan.oneandroid.model.bean.ProjectClassify
import kotlinx.coroutines.launch

/**
 * <pre>
 *   Created by zhpan on 2020/7/23.
 *   Description:
 * </pre>
 */
class ProjectViewModel() : BaseViewModel<ProjectRepository>() {

  private val _responseLiveData = ResponseMutableLiveData<List<ProjectClassify>>()
  val responseLiveData: ResponseMutableLiveData<List<ProjectClassify>> = _responseLiveData

  fun getProjectTrees(
    showLoading: Boolean
  ) {
    viewModelScope.launch {
      repository.getProjectTrees(_responseLiveData, showLoading)
    }
  }
}