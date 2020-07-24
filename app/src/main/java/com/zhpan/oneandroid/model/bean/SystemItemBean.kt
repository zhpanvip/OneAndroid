package com.zhpan.oneandroid.model.bean

/**
 *
 * @author zhangpan
 * @date 2020/7/24
 */
data class SystemItemBean(
    val children: List<Any>,
    val courseId: Int,
    val id: Int,
    val name: String,
    val order: Int,
    val parentChapterId: Int,
    val visible: Int
)