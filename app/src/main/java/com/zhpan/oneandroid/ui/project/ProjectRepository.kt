package com.zhpan.oneandroid.ui.project

import com.zhpan.library.base.BaseRepository
import com.zhpan.library.network.ResponseMutableLiveData
import com.zhpan.oneandroid.api.RetrofitManager
import com.zhpan.oneandroid.model.bean.ProjectClassify

/**
 * <pre>
 *   Created by zhpan on 2020/7/23.
 *   Description:
 * </pre>
 */
class ProjectRepository : BaseRepository() {

  suspend fun getProjectTrees(
    responseLiveData: ResponseMutableLiveData<List<ProjectClassify>>,
    showLoading: Boolean
  ) {
    executeRequest(
      { RetrofitManager.getApiService().getProjectClassify() },
      responseLiveData,
      showLoading
    )
  }
}