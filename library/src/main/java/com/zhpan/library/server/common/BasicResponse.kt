package com.zhpan.library.server.common

/**
 * Created by zhpan on 2020/7/8.
 * Description:
 */
open class BasicResponse<T> {
  var errorCode = 0
    private set
  var errorMsg: String? = null
  var data: T? = null
    private set
  var dataState: DataState? = null
  var exception: Throwable? = null
  val success: Boolean
    get() = errorCode == ErrorCode.SUCCESS

  companion object {
    const val ERROR_CODE_SUCCESS = 0
  }

}

enum class DataState {
  STATE_LOADING, // 加载中
  STATE_SUCCESS, // 成功
  STATE_EMPTY, // 数据为null
  STATE_FAILED, // 接口请求成功但是服务器返回error
  STATE_ERROR, // 请求失败
  STATE_FINISH // 未知
}