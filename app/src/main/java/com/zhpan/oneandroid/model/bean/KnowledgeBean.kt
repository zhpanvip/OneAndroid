package com.zhpan.oneandroid.model.bean

import java.io.Serializable

/**
 *
 * @author zhangpan
 * @date 2020/7/24
 */
data class KnowledgeBean(
    val children: List<KnowledgeBean>,
    val courseId: Int,
    val id: String,
    val name: String,
    val order: Int,
    val parentChapterId: Int,
    val visible: Int
) : Serializable