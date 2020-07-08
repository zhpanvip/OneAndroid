package com.zhpan.oneandroid.api

import com.zhpan.oneandroid.module.request.ArticleWrapper
import com.zhpan.oneandroid.module.request.LoginRequest
import com.zhpan.oneandroid.module.response.LoginResponse
import io.reactivex.Observable
import retrofit2.http.*

/**
 * Created by dell on 2017/4/1.
 */
interface ApiService {
    @get:GET("article/list/0/json")
    val article: Observable<ArticleWrapper?>?

    /**
     * 登录 appId secret
     * 使用实体类作为参数
     *
     * @return
     */
    @POST("user/login")
    fun login(@Body request: LoginRequest?): Observable<LoginResponse?>?

    /**
     * 使用map作为参数
     *
     * @param map
     * @return
     */
    @FormUrlEncoded
    @POST("user/login")
    fun login(@FieldMap map: Map<String, String>?): Observable<LoginResponse?>?
}