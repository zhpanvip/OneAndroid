package com.zhpan.oneandroid.repository

import com.zhpan.oneandroid.api.ApiService
import com.zhpan.oneandroid.api.RetrofitCreator

/**
 * <pre>
 * Created by zhpan on 2020/7/11.
 * Description:
</pre> *
 */
open class BaseRepository {

    fun getApiService(): ApiService? {
        return RetrofitCreator.apiService
    }
}