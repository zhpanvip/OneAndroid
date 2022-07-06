package com.zhpan.oneandroid.api

import com.zhpan.library.server.common.BasicResponse
import com.zhpan.oneandroid.model.bean.User
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface LoginAPI {

  @FormUrlEncoded
  @POST("user/login")
  suspend fun login(
    @Field("username") username: String,
    @Field("password") password: String
  ): BasicResponse<User>
}