package com.zhpan.library.base

import androidx.fragment.app.FragmentActivity
import com.trello.rxlifecycle2.LifecycleTransformer
import com.trello.rxlifecycle2.android.ActivityEvent

/**
 *
 * @author zhangpan
 * @date 2020/7/16
 */
interface IActivityHost :IPageHost{

    fun getActivity(): FragmentActivity?

    fun <T>  bindUntilEvent(event: ActivityEvent): LifecycleTransformer<T>
}