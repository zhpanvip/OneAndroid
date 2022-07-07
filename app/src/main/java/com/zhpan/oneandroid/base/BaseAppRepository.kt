package com.zhpan.oneandroid.base

import com.zhpan.library.base.BaseRepository
import com.zhpan.oneandroid.api.ApiService
import com.zhpan.oneandroid.api.RetrofitCreator

/**
 * <pre>
 * Created by zhpan on 2020/7/11.
 * Description:
</pre> *
 */
open class BaseAppRepository :BaseRepository(){

    fun getApiService(): ApiService {
        return RetrofitCreator.apiService
    }
}