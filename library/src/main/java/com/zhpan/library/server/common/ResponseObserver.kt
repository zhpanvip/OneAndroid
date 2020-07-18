package com.zhpan.library.server.common

import android.widget.Toast
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.ToastUtils
import com.blankj.utilcode.util.Utils
import com.google.gson.JsonParseException
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException
import com.zhpan.library.R
import com.zhpan.library.server.exception.NoDataExceptionException
import com.zhpan.library.server.exception.ServerResponseException
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import org.json.JSONException
import java.io.InterruptedIOException
import java.net.ConnectException
import java.net.UnknownHostException
import java.text.ParseException

/**
 * Created by zhpan on 2017/4/18.
 */
abstract class ResponseObserver<T> : Observer<T> {
    override fun onSubscribe(d: Disposable) {}
    override fun onNext(response: T) {
        onSuccess(response)
        onFinish()
    }

    override fun onError(e: Throwable) {
        LogUtils.e("Retrofit", e.message)
        if (e is HttpException) {     //   HTTP错误
            onException(ExceptionReason.BAD_NETWORK)
        } else if (e is ConnectException
            || e is UnknownHostException
        ) {   //   连接错误
            onException(ExceptionReason.CONNECT_ERROR)
        } else if (e is InterruptedIOException) {   //  连接超时
            onException(ExceptionReason.CONNECT_TIMEOUT)
        } else if (e is JsonParseException
            || e is JSONException
            || e is ParseException
        ) {   //  解析错误
            onException(ExceptionReason.PARSE_ERROR)
        } else if (e is ServerResponseException) {
            onFail(e.errorCode, e.message)
        } else if (e is NoDataExceptionException) {
            onSuccess(null)
        } else {
            onException(ExceptionReason.UNKNOWN_ERROR)
        }
        onFinish()
    }

    override fun onComplete() {}

    /**
     * 请求成功
     *
     * @param response 服务器返回的数据
     */
    abstract fun onSuccess(response: T?)
    /**
     * 服务器返回数据，但响应码不为200
     *
     */
    /**
     * 服务器返回数据，但响应码不为1000
     */
    open fun onFail(errorCode: Int, message: String?) {
        if (message != null) {
            ToastUtils.showShort(message)
        }
    }

    open fun onFinish() {}

    /**
     * 请求异常
     *
     * @param reason
     */
    fun onException(reason: ExceptionReason?) {
        @androidx.annotation.StringRes var errorMsg: Int = when (reason) {
            ExceptionReason.CONNECT_ERROR -> R.string.connect_error
            ExceptionReason.CONNECT_TIMEOUT -> R.string.connect_timeout
            ExceptionReason.BAD_NETWORK -> R.string.bad_network
            ExceptionReason.PARSE_ERROR -> R.string.parse_error
            ExceptionReason.UNKNOWN_ERROR -> R.string.unknown_error
            else -> R.string.unknown_error
        }
        val errorMessage = Utils.getApp().resources.getString(errorMsg)
        ToastUtils.showShort(errorMessage)
        onFail(0,errorMessage)
    }

    /**
     * 请求网络失败原因
     */
    enum class ExceptionReason {
        /**
         * 解析数据失败
         */
        PARSE_ERROR,

        /**
         * 网络问题
         */
        BAD_NETWORK,

        /**
         * 连接错误
         */
        CONNECT_ERROR,

        /**
         * 连接超时
         */
        CONNECT_TIMEOUT,

        /**
         * 未知错误
         */
        UNKNOWN_ERROR
    }
}