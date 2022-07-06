package com.zhpan.library.network

import androidx.lifecycle.MutableLiveData
import com.zhpan.library.server.common.BasicResponse


class StateLiveData<T> : MutableLiveData<BasicResponse<T>>() {
}