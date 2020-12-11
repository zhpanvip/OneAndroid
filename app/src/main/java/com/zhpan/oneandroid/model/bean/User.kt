package com.zhpan.oneandroid.model.bean

import android.text.TextUtils
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by zhpan on 2017/10/25.
 * Description:
 */
@Entity(tableName = "user")
class User {
    @PrimaryKey
    val id: String? = null
    val admin: Boolean = false
    val chapterTops: List<Any>? = null

    @ColumnInfo(name = "coinCount")
    val coinCount: Int = 0
    val collectIds: List<Int>? = null
    val email: String? = null
    val icon: String? = null
    val nickname: String? = null
    val password: String? = null
    val publicName: String? = null
    val token: String? = null
    val type: Int = 0
    val username: String? = null

    fun isLogin(): Boolean {
        return !id.isNullOrEmpty() && !nickname.isNullOrEmpty()
    }


}