package com.zhpan.oneandroid.model.response

import com.zhpan.oneandroid.model.bean.Article

class ArticleResponse() :BaseResponse(){
    var datas: List<Article>? = null
    var curPage: Int? = 0
    var offset: Int? = 0
    var over: Boolean? = false
    var pageCount: Int? = 0
    var size: Int? = 0
    var total: Int? = 0
}