package com.zhpan.oneandroid.module.request

class ArticleWrapper {
    var datas: List<Article>? = null

    class Article {
        var title: String? = null
        var link: String? = null
        var author: String? = null
        var publishTime: Long = 0
    }
}