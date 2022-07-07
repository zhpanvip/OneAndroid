package com.zhpan.oneandroid.api

import com.zhpan.library.server.common.BasicResponse
import com.zhpan.oneandroid.model.bean.BannerBean
import com.zhpan.oneandroid.model.bean.User
import com.zhpan.oneandroid.model.response.ArticleResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface LoginAPI {

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
}