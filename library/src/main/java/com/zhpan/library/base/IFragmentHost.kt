package com.zhpan.library.base

import android.app.Activity
import androidx.fragment.app.FragmentActivity
import com.trello.rxlifecycle2.LifecycleTransformer
import com.trello.rxlifecycle2.android.FragmentEvent

/**
 * @author zhangpan
 * @date 2020/7/16
 */
interface IFragmentHost:IPageHost {

    fun getActivity(): Activity?

    fun <T> bindUntilEvent(event: FragmentEvent): LifecycleTransformer<T>?
}