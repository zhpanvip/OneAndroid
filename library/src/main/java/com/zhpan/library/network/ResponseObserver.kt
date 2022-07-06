package com.zhpan.library.network

import androidx.lifecycle.Observer
import com.blankj.utilcode.util.ToastUtils
import com.zhpan.library.server.common.BasicResponse

abstract class ResponseObserver<T> : Observer<BasicResponse<T>> {
  override fun onChanged(response: BasicResponse<T>?) {
    if (response?.success!! && response.exception == null) {
      onSuccess(response.data)
    } else {
      if (response.exception !== null) {
        onException(response.exception)
      } else {
        onFail(response.errorCode, response.errorMsg)
      }
    }
  }

  abstract fun onSuccess(data: T?)

  abstract fun onFail(errorCode: Int, errorMsg: String?)

  private fun onException(exception: Throwable?) {
    ToastUtils.showShort(exception.toString())
  }

}