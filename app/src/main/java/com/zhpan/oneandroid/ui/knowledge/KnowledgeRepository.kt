package com.zhpan.oneandroid.ui.knowledge

import com.zhpan.library.base.BaseRepository
import com.zhpan.library.network.ResponseMutableLiveData
import com.zhpan.oneandroid.api.RetrofitCreator
import com.zhpan.oneandroid.model.bean.KnowledgeBean

/**
 *
 * @author zhangpan
 * @date 2020/7/24
 */
class KnowledgeRepository : BaseRepository() {

  suspend fun getSystemClassify(
    responseLiveData: ResponseMutableLiveData<List<KnowledgeBean>>,
    showLoading: Boolean
  ) {
    executeRequest({
      RetrofitCreator.getLoginAPI().getSystemClassify()
    }, responseLiveData, showLoading)
  }
}