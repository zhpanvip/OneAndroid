package com.zhpan.oneandroid.model.bean

import com.zhpan.oneandroid.model.response.BaseResponse

/**
 *
 * @author zhangpan
 * @date 2020/7/22
 */
class OfficialAccountBean : BaseResponse(){
    /**
     *  "children": [],
    "courseId": 13,
    "id": 408,
    "name": "鸿洋",
    "order": 190000,
    "parentChapterId": 407,
    "userControlSetTop": false,
    "visible": 1
     */

    var id: Int = 0;

    var courseId: Int = 0;

    var name: String? = null

    var order: Int = 0

    var parentChapterId: Int = 0;

    var userControlSetTop: Boolean = false

    var visible: Int = 0;
}