package com.zhpan.library.utils

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.TextView
import java.util.*

/**
 * author : Android 轮子哥
 * github : https://github.com/getActivity/AndroidProject
 * time   : 2018/10/18
 * desc   : 文本输入辅助类，通过管理多个 TextView 输入是否为空来启用或者禁用按钮的点击事件
 * blog   : https://www.jianshu.com/p/fd3795e8a6b3
 */
class InputTextHelper private constructor(view: View?, alpha: Boolean) :
    TextWatcher {
    /** 操作按钮的View  */
    private val mView: View

    /** 是否禁用后设置半透明度  */
    private val isAlpha: Boolean

    /** TextView集合  */
    private var mViewSet: MutableList<TextView>? = null

    /** 输入监听器  */
    private var mListener: OnInputTextListener? = null

    /**
     * 添加 TextView
     *
     * @param views     传入单个或者多个 TextView
     */
    fun addViews(views: MutableList<TextView>?) {
        if (views == null) {
            return
        }
        if (mViewSet == null) {
            mViewSet = views
        } else {
            mViewSet!!.addAll(views)
        }
        for (view in views) {
            view.addTextChangedListener(this)
        }

        // 触发一次监听
        notifyChanged()
    }

    /**
     * 添加 TextView
     *
     * @param views     传入单个或者多个 TextView
     */
    fun addViews(vararg views: TextView) {
        if (views == null) {
            return
        }
        if (mViewSet == null) {
            mViewSet = ArrayList(views.size)
        }
        for (view in views) {
            // 避免重复添加
            if (!mViewSet!!.contains(view)) {
                view.addTextChangedListener(this)
                mViewSet!!.add(view)
            }
        }
        // 触发一次监听
        notifyChanged()
    }

    /**
     * 移除 TextView 监听，避免内存泄露
     */
    fun removeViews(vararg views: TextView) {
        if (mViewSet != null && mViewSet!!.size > 0) {
            for (view in views) {
                view.removeTextChangedListener(this)
                mViewSet!!.remove(view)
            }
            // 触发一次监听
            notifyChanged()
        }
    }

    /**
     * 移除所有 TextView 监听，避免内存泄露
     */
    fun removeAllViews() {
        if (mViewSet == null) {
            return
        }
        for (view in mViewSet!!) {
            view.removeTextChangedListener(this)
        }
        mViewSet!!.clear()
        mViewSet = null
    }

    /**
     * 设置输入监听
     */
    fun setListener(listener: OnInputTextListener?) {
        mListener = listener
    }

    /**
     * [TextWatcher]
     */
    override fun beforeTextChanged(
        s: CharSequence,
        start: Int,
        count: Int,
        after: Int
    ) {
    }

    override fun onTextChanged(
        s: CharSequence,
        start: Int,
        before: Int,
        count: Int
    ) {
    }

    override fun afterTextChanged(s: Editable) {
        notifyChanged()
    }

    /**
     * 通知更新
     */
    fun notifyChanged() {
        if (mViewSet == null) {
            return
        }

        // 重新遍历所有的输入
        for (view in mViewSet!!) {
            if ("" == view.text.toString()) {
                setEnabled(false)
                return
            }
        }
        if (mListener != null) {
            setEnabled(mListener!!.onInputChange(this))
        } else {
            setEnabled(true)
        }
    }

    /**
     * 设置 View 的事件
     *
     * @param enabled               启用或者禁用 View 的事件
     */
    fun setEnabled(enabled: Boolean) {
        if (enabled == mView.isEnabled) {
            return
        }
        if (enabled) {
            //启用View的事件
            mView.isEnabled = true
            if (isAlpha) {
                //设置不透明
                mView.alpha = 1f
            }
        } else {
            //禁用View的事件
            mView.isEnabled = false
            if (isAlpha) {
                //设置半透明
                mView.alpha = 0.5f
            }
        }
    }

    class Builder(
        /** 当前的 Activity  */
        private val mActivity: Activity
    ) {

        /** 操作按钮的 View  */
        private var mView: View? = null

        /** 是否禁用后设置半透明度  */
        private var isAlpha = false

        /**  TextView集合  */
        private val mViewSet: MutableList<TextView> =
            ArrayList()

        /** 文本  */
        private var mListener: OnInputTextListener? = null
        fun addView(view: TextView): Builder {
            mViewSet.add(view)
            return this
        }

        fun setMain(view: View?): Builder {
            mView = view
            return this
        }

        fun setAlpha(alpha: Boolean): Builder {
            isAlpha = alpha
            return this
        }

        fun setListener(listener: OnInputTextListener?): Builder {
            mListener = listener
            return this
        }

        fun build(): InputTextHelper {
            val helper = InputTextHelper(mView, isAlpha)
            helper.addViews(mViewSet)
            helper.setListener(mListener)
            mActivity.application
                .registerActivityLifecycleCallbacks(TextInputLifecycle(mActivity, helper))
            return helper
        }

    }

    private class TextInputLifecycle(
        activity: Activity,
        helper: InputTextHelper
    ) : Application.ActivityLifecycleCallbacks {
        private var mActivity: Activity?
        private var mTextHelper: InputTextHelper?
        override fun onActivityCreated(
            activity: Activity,
            savedInstanceState: Bundle?
        ) {
        }

        override fun onActivityStarted(activity: Activity) {}
        override fun onActivityResumed(activity: Activity) {}
        override fun onActivityPaused(activity: Activity) {}
        override fun onActivityStopped(activity: Activity) {}
        override fun onActivitySaveInstanceState(
            activity: Activity,
            outState: Bundle
        ) {
        }

        override fun onActivityDestroyed(activity: Activity) {
            if (mActivity != null && mActivity === activity) {
                mTextHelper!!.removeAllViews()
                mActivity!!.application.registerActivityLifecycleCallbacks(this)
                mTextHelper = null
                mActivity = null
            }
        }

        init {
            mActivity = activity
            mTextHelper = helper
        }
    }

    /**
     * 文本变化监听器
     */
    interface OnInputTextListener {
        /**
         * 输入发生了变化
         * @return          返回按钮的 Enabled 状态
         */
        fun onInputChange(helper: InputTextHelper?): Boolean
    }

    companion object {
        /**
         * 创建 Builder
         */
        fun with(activity: Activity): Builder {
            return Builder(activity)
        }
    }

    /**
     * 构造函数
     *
     * @param view              跟随 TextView 输入为空来判断启动或者禁用这个 View
     * @param alpha             是否需要设置透明度
     */
    init {
        requireNotNull(view) { "are you ok?" }
        mView = view
        isAlpha = alpha
    }
}