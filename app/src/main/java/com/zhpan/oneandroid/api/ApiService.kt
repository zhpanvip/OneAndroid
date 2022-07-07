package com.zhpan.oneandroid.api

import com.zhpan.oneandroid.model.bean.BannerBean
import com.zhpan.oneandroid.model.bean.KnowledgeBean
import com.zhpan.oneandroid.model.bean.OfficialAccountBean
import com.zhpan.oneandroid.model.bean.ProjectClassify
import com.zhpan.oneandroid.model.response.ArticleResponse
import com.zhpan.oneandroid.model.response.ProjectResponse
import com.zhpan.oneandroid.model.bean.User
import io.reactivex.Observable
import retrofit2.http.*

/**
 * Created by dell on 2017/4/1.
 */
interface ApiService {



    @GET("user_article/list/{page}/json")
    fun getSquareArticles(@Path("page") page: Int): Observable<ArticleResponse>

    @GET("wxarticle/chapters/json")
    fun getOfficialAccounts(): Observable<List<OfficialAccountBean>>

    @GET("wxarticle/list/{accountId}/{page}/json")
    fun getWechatArticles(
        @Path("accountId") accountId: String?,
        @Path("page") page: Int
    ): Observable<ArticleResponse>

    @FormUrlEncoded
    @POST("user/login")
    fun login(
        @Field("username") username: String,
        @Field("password") password: String
    ): Observable<User>

    @GET("banner/json")
    fun getBannerData(): Observable<List<BannerBean>>

    @GET("project/tree/json")
    fun getProjectClassify(): Observable<List<ProjectClassify>>

    @GET("project/list/{page}/json")
    fun getProjectList(
        @Path("page") page: Int,
        @Query("cid") cid: String
    ): Observable<ProjectResponse>

    @GET("tree/json")
    fun getSystemClassify(): Observable<List<KnowledgeBean>>

    @GET("article/list/{page}/json")
    fun getKnowledgeArticles(
        @Path("page") page: Int,
        @Query("cid") cid: String
    ): Observable<ArticleResponse>

}