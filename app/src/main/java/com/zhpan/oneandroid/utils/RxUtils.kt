package com.zhpan.oneandroid.utils

import com.trello.rxlifecycle2.android.ActivityEvent
import com.trello.rxlifecycle2.android.FragmentEvent
import com.zhpan.library.base.IActivityHost
import com.zhpan.library.base.IFragmentHost
import com.zhpan.library.utils.LoadingHelper
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

object RxUtils {
    /**
     * @param activity    Activity
     * @param showLoading 是否显示Loading
     * @return 转换后的ObservableTransformer
     */
    fun <T> rxSchedulerHelper(
        host: IActivityHost?,
        showLoading: Boolean
    ): ObservableTransformer<T, T>? {
        return if (host?.getActivity() == null) {
            rxSchedulerHelper()
        } else ObservableTransformer { observable ->
            val compose = observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(host.bindUntilEvent(ActivityEvent.DESTROY))
            if (showLoading) {
                compose.compose(LoadingHelper.applyProgressBar(host.getActivity()!!))
            } else compose
        }
    }

    /**
     * @param fragment    fragment
     * @param showLoading 是否显示Loading
     * @return 转换后的ObservableTransformer
     */
    fun <T> rxSchedulerHelper(
        fragment: IFragmentHost?,
        showLoading: Boolean
    ): ObservableTransformer<T, T>? {
        return if (fragment?.getActivity() == null) {
            rxSchedulerHelper()
        } else ObservableTransformer { upstream ->
            val observable = upstream.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(fragment.bindUntilEvent(FragmentEvent.DESTROY))
            if (showLoading) {
                observable.compose(LoadingHelper.applyProgressBar(fragment.getActivity()!!))
            } else observable
        }
    }


    /**
     * 统一线程处理
     *
     * @return 转换后的ObservableTransformer
     */
    fun <T> rxSchedulerHelper(): ObservableTransformer<T, T> {
        return ObservableTransformer { observable: Observable<T> ->
            observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        }
    }
}