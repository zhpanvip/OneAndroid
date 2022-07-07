package com.zhpan.library.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zhpan.library.network.StateLiveData
import com.zhpan.library.server.common.BasicResponse
import com.zhpan.library.server.common.DataState

/**
 * <pre>
 *   Created by zhpan on 2020/7/5.
 *   Description:
 * </pre>
 */
abstract class NewBaseViewModel<BP : BaseRepository> : ViewModel() {

  val repository: BP by lazy {
    createRepository()
  }

  val loadingDataState: MutableLiveData<DataState> by lazy {
    repository.loadingStateLiveData
  }

  abstract fun createRepository(): BP

  suspend fun <T : Any> request(
    block: suspend () -> BasicResponse<T>,
    stateLiveData: StateLiveData<T>
  ) {
    var baseResp = BasicResponse<T>()
    try {
      baseResp.dataState = DataState.STATE_LOADING
      //开始请求数据
      val invoke = block.invoke()
      //将结果复制给baseResp
      baseResp = invoke
      if (baseResp.errorCode == 0) {
        //请求成功，判断数据是否为空，
        //因为数据有多种类型，需要自己设置类型进行判断
        if (baseResp.data == null || baseResp.data is List<*> && (baseResp.data as List<*>).size == 0) {
          //TODO: 数据为空,结构变化时需要修改判空条件
          baseResp.dataState = DataState.STATE_EMPTY
        } else {
          //请求成功并且数据为空的情况下，为STATE_SUCCESS
          baseResp.dataState = DataState.STATE_SUCCESS
        }

      } else {
        //服务器请求错误
        baseResp.dataState = DataState.STATE_FAILED
      }
    } catch (e: Exception) {
      //非后台返回错误，捕获到的异常
      baseResp.dataState = DataState.STATE_ERROR
      baseResp.exception = e
    } finally {
      stateLiveData.postValue(baseResp)
    }
  }
}