package com.zhpan.library.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zhpan.library.network.StateLiveData
import com.zhpan.library.server.common.BasicResponse
import com.zhpan.library.server.common.DataState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


/**
 * <pre>
 *   Created by zhpan on 2020/7/5.
 *   Description:
 * </pre>
 */
open class BaseViewModel : ViewModel() {

    open class UiState<T>(
        val isLoading: Boolean = false,
        val isRefresh: Boolean = false,
        val isSuccess: T? = null,
        val isError: String? = null
    )


    open class BaseUiModel<T>(
        var showLoading: Boolean = false,
        var showError: String? = null,
        var showSuccess: T? = null,
        var showEnd: Boolean = false, // 加载更多
        var isRefresh: Boolean = false // 刷新
    )

    val mException: MutableLiveData<Throwable> = MutableLiveData()


    fun launchOnUI(block: suspend CoroutineScope.() -> Unit) {

        viewModelScope.launch { block() }

    }

    suspend fun <T> launchOnIO(block: suspend CoroutineScope.() -> T) {
        withContext(Dispatchers.IO) {
            block
        }
    }


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