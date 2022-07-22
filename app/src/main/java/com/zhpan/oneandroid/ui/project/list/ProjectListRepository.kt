package com.zhpan.oneandroid.ui.project.list

import com.zhpan.library.base.BaseRepository
import com.zhpan.library.network.ResponseMutableLiveData
import com.zhpan.oneandroid.api.RetrofitCreator
import com.zhpan.oneandroid.model.response.ProjectResponse

/**
 * <pre>
 *   Created by zhpan on 2020/7/24.
 *   Description:
 * </pre>
 */
class ProjectListRepository : BaseRepository() {
  suspend fun getProjectList(
    responseLiveData: ResponseMutableLiveData<ProjectResponse>,
    showLoading: Boolean,
    page: Int,
    cid: String
  ) {
    executeRequest(
      { RetrofitCreator.getLoginAPI().getProjectList(page, cid) },
      responseLiveData,
      showLoading
    )
  }
}