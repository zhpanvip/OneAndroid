package com.zhpan.oneandroid.model.bean


/**
 * <pre>
 *   Created by zhpan on 2020/7/23.
 *   Description:
 * </pre>
 */
class ProjectBean {
    /**
     * apkLink :
     * audit : 1
     * author : VIPyinzhiwei
     * canEdit : false
     * chapterId : 294
     * chapterName : 完整项目
     * collect : false
     * courseId : 13
     * desc : 基于 Kotlin 语言仿写「开眼 Eyepetizer」的一个短视频 Android 客户端项目，采用 Jetpack + 协程实现的 MVVM 架构。
     *
     * 值得一提的是，所有 UI 都是经过标注工具测量后的，无论是字体颜色、大小、间距等几乎都是像素级模仿的「开眼 Eyepetizer」Android 客户端 App，对应的 v6.3.1 版本（目前更新版）。
     * descMd :
     * envelopePic : https://www.wanandroid.com/blogimgs/c90df328-30b0-4fc9-906a-c661ed450cad.png
     * fresh : false
     * id : 13976
     * link : https://www.wanandroid.com/blog/show/2774
     * niceDate : 2020-06-22 00:43
     * niceShareDate : 2020-06-22 00:43
     * origin :
     * prefix :
     * projectLink : https://github.com/VIPyinzhiwei/Eyepetizer
     * publishTime : 1592757812000
     * realSuperChapterId : 293
     * selfVisible : 0
     * shareDate : 1592757812000
     * shareUser :
     * superChapterId : 294
     * superChapterName : 开源项目主Tab
     * tags : [{"name":"项目","url":"/project/list/1?cid=294"}]
     * title : 一款高仿 Eyepetizer | 开眼短视频的 MVVM 开源项目
     * type : 0
     * userId : -1
     * visible : 1
     * zan : 0
     */
    var apkLink: String? = null
    var audit = 0
    var author: String? = null
    var isCanEdit = false
    var chapterId = 0
    var chapterName: String? = null
    var isCollect = false
    var courseId = 0
    var desc: String? = null
    var descMd: String? = null
    var envelopePic: String? = null
    var isFresh = false
    var id = 0
    var link: String? = null
    var niceDate: String? = null
    var niceShareDate: String? = null
    var origin: String? = null
    var prefix: String? = null
    var projectLink: String? = null
    var publishTime: Long = 0
    var realSuperChapterId = 0
    var selfVisible = 0
    var shareDate: Long = 0
    var shareUser: String? = null
    var superChapterId = 0
    var superChapterName: String? = null
    var title: String? = null
    var type = 0
    var userId = 0
    var visible = 0
    var zan = 0
    var tags: List<TagsBean>? = null
}