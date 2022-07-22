package com.zhpan.oneandroid.api

import com.zhpan.library.server.common.BasicResponse
import com.zhpan.oneandroid.model.bean.BannerBean
import com.zhpan.oneandroid.model.bean.KnowledgeBean
import com.zhpan.oneandroid.model.bean.OfficialAccountBean
import com.zhpan.oneandroid.model.bean.ProjectClassify
import com.zhpan.oneandroid.model.response.ArticleResponse
import com.zhpan.oneandroid.model.response.ProjectResponse
import com.zhpan.oneandroid.model.bean.User
import retrofit2.http.*

/**
 * Created by dell on 2017/4/1.
 */
interface ApiService {

    @FormUrlEncoded
    @POST("user/login")
    suspend fun login(
        @Field("username") username: String,
        @Field("password") password: String
    ): BasicResponse<User>

    @GET("banner/json")
    suspend fun getBannerData(): BasicResponse<List<BannerBean>>

    @GET("/article/list/{page}/json")
    suspend fun getHomeArticles(@Path("page") page: Int): BasicResponse<ArticleResponse>

    @GET("article/list/{page}/json")
    suspend fun getKnowledgeArticles(
        @Path("page") page: Int,
        @Query("cid") cid: String
    ): BasicResponse<ArticleResponse>

    @GET("tree/json")
    suspend fun getSystemClassify(): BasicResponse<List<KnowledgeBean>>

    @GET("wxarticle/list/{accountId}/{page}/json")
    suspend fun getWechatArticles(
        @Path("accountId") accountId: String?,
        @Path("page") page: Int
    ): BasicResponse<ArticleResponse>

    @GET("wxarticle/chapters/json")
    suspend fun getOfficialAccounts(): BasicResponse<List<OfficialAccountBean>>

    @GET("project/tree/json")
    suspend fun getProjectClassify(): BasicResponse<List<ProjectClassify>>

    @GET("project/list/{page}/json")
    suspend fun getProjectList(
        @Path("page") page: Int,
        @Query("cid") cid: String
    ): BasicResponse<ProjectResponse>

    @GET("user_article/list/{page}/json")
    suspend fun getSquareArticles(@Path("page") page: Int): BasicResponse<ArticleResponse>
}