package com.zhpan.oneandroid.ui.project.list

import androidx.lifecycle.viewModelScope
import com.zhpan.library.base.BaseViewModel
import com.zhpan.library.network.ResponseLiveData
import com.zhpan.library.network.ResponseMutableLiveData
import com.zhpan.oneandroid.model.response.ProjectResponse
import kotlinx.coroutines.launch

/**
 * <pre>
 *   Created by zhpan on 2020/7/24.
 *   Description:
 * </pre>
 */
class ProjectListViewModel() : BaseViewModel<ProjectListRepository>() {
  private val _responseLiveData = ResponseMutableLiveData<ProjectResponse>()
  val responseLiveData: ResponseLiveData<ProjectResponse> = _responseLiveData

  fun getProjectList(
    showLoading: Boolean,
    page: Int,
    cid: String
  ) {
    viewModelScope.launch {
      repository.getProjectList(_responseLiveData, showLoading, page, cid)
    }
  }
}