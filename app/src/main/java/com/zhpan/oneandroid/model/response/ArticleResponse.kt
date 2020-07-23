package com.zhpan.oneandroid.model.response

import com.zhpan.oneandroid.model.bean.ArticleBean

class ArticleResponse() :BaseResponse(){
    var datas: List<ArticleBean>? = null
    var curPage: Int? = 0
    var offset: Int? = 0
    var over: Boolean? = false
    var pageCount: Int? = 0
    var size: Int? = 0
    var total: Int? = 0
}