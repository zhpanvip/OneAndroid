package com.zhpan.library.base

import androidx.lifecycle.MutableLiveData
import com.zhpan.library.network.StateLiveData
import com.zhpan.library.server.common.BasicResponse
import com.zhpan.library.server.common.DataState
import java.lang.Exception

open class BaseRepository {

  val loadingStateLiveData: MutableLiveData<DataState> by lazy {
    MutableLiveData<DataState>()
  }

  /**
   * 发起请求
   * @param block 真正执行的函数回调
   * @param stateLiveData 观察状态的LiveData
   */
  suspend fun <T : Any> executeRequest(
    block: suspend () -> BasicResponse<T>,
    stateLiveData: StateLiveData<T>,
    showLoading: Boolean = true
  ) {
    var response = BasicResponse<T>()
    try {
      if (showLoading) {
        loadingStateLiveData.postValue(DataState.STATE_LOADING)
      }
      val invoke = block.invoke()
      response = invoke
      if (response.errorCode == BasicResponse.ERROR_CODE_SUCCESS) {
        if (isEmptyData(response.data)) {
          response.dataState = DataState.STATE_EMPTY
        } else {
          response.dataState = DataState.STATE_SUCCESS
        }
      } else {
        response.dataState = DataState.STATE_FAILED
        // throw ServerResponseException(response.errorCode, response.errorMsg)
      }
    } catch (e: Exception) {
      response.dataState = DataState.STATE_ERROR
      response.exception = e
    } finally {
      stateLiveData.postValue(response)
      if (showLoading) {
        loadingStateLiveData.postValue(DataState.STATE_FINISH)
      }
    }
  }

  private fun <T> isEmptyData(data: T?): Boolean {
    return data == null || data is List<*> && (data as List<*>).isEmpty()
  }

}