package com.zhpan.oneandroid.ui.wechat

import androidx.lifecycle.viewModelScope
import com.zhpan.library.base.BaseViewModel
import com.zhpan.library.network.ResponseMutableLiveData
import com.zhpan.oneandroid.model.bean.OfficialAccountBean
import kotlinx.coroutines.launch

/**
 *
 * @author zhangpan
 * @date 2020/7/22
 */
class OfficialAccountViewModel() :
  BaseViewModel<OfficialAccountRepository>() {
  private val _responseLiveData = ResponseMutableLiveData<List<OfficialAccountBean>>()
  val responseLiveData = _responseLiveData

  fun getOfficialAccountViewModel(
    showLoading: Boolean
  ) {
    viewModelScope.launch {
      repository.getOfficialAccounts(_responseLiveData, showLoading);
    }
  }
}