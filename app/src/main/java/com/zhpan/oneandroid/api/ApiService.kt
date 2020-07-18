package com.zhpan.oneandroid.api

import com.zhpan.oneandroid.model.response.ArticleResponse
import com.zhpan.oneandroid.model.bean.BannerBean
import com.zhpan.oneandroid.model.response.LoginResponse
import io.reactivex.Observable
import retrofit2.http.*

/**
 * Created by dell on 2017/4/1.
 */
interface ApiService {

    @GET("/article/list/{page}/json")
    fun getHomeArticles(@Path("page") page: Int): Observable<ArticleResponse>

    @GET("user_article/list/{page}/json")
    fun getSquareArticles(@Path("page") page: Int): Observable<ArticleResponse>

    /**
     * 使用map作为参数
     *
     * @param map
     * @return
     */
    @FormUrlEncoded
    @POST("user/login")
    fun login(
        @Field("username") username: String,
        @Field("password") password: String
    ): Observable<LoginResponse?>?

    @GET("banner/json")
    fun getBannerData(): Observable<List<BannerBean>>
}