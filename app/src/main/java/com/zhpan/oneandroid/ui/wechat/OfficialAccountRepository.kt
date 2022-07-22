package com.zhpan.oneandroid.ui.wechat

import com.zhpan.library.base.BaseRepository
import com.zhpan.library.network.ResponseMutableLiveData
import com.zhpan.oneandroid.api.RetrofitCreator
import com.zhpan.oneandroid.model.bean.OfficialAccountBean

/**
 *
 * @author zhangpan
 * @date 2020/7/22
 */
class OfficialAccountRepository : BaseRepository() {
  suspend fun getOfficialAccounts(
    responseLiveData: ResponseMutableLiveData<List<OfficialAccountBean>>,
    showLoading: Boolean
  ) {
    executeRequest(
      { RetrofitCreator.getLoginAPI().getOfficialAccounts() },
      responseLiveData,
      showLoading
    )
  }
}