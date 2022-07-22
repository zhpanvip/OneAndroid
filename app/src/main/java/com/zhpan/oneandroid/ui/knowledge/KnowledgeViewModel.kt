package com.zhpan.oneandroid.ui.knowledge

import androidx.lifecycle.viewModelScope
import com.zhpan.library.base.BaseViewModel
import com.zhpan.library.network.ResponseMutableLiveData
import com.zhpan.oneandroid.model.bean.KnowledgeBean
import kotlinx.coroutines.launch

/**
 *
 * @author zhangpan
 * @date 2020/7/24
 */
class KnowledgeViewModel() : BaseViewModel<KnowledgeRepository>() {
  private val _responseLiveData = ResponseMutableLiveData<List<KnowledgeBean>>()
  val responseLiveData: ResponseMutableLiveData<List<KnowledgeBean>> = _responseLiveData
  fun getSystemClassify(
    showLoading: Boolean
  ) {
    viewModelScope.launch {
      repository.getSystemClassify(_responseLiveData, showLoading)
    }
  }
}