package com.zhpan.library.base

import android.annotation.SuppressLint
import android.content.Context

/**
 * @Description:
 * @Author: zhangpan
 * @Date: 2022/7/7 14:37
 * @Email: pan.zhang@upuphone.com
 */
abstract class BaseAndroidViewModel<T : BaseRepository>(@field:SuppressLint("StaticFieldLeak") var context: Context) : NewBaseViewModel<T>()