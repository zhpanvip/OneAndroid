package com.zhpan.library.base

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import com.blankj.utilcode.util.BarUtils
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel


/**
 * <pre>
 *   Created by zhpan on 2020/7/5.
 *   Description:
 * </pre>
 */
abstract class BaseActivity<VM : BaseViewModel, VB : ViewDataBinding> : RxAppCompatActivity(),
    CoroutineScope by MainScope(), IActivityHost {

    protected val mBinding: VB by lazy {
        DataBindingUtil.setContentView(this, getLayoutId()) as VB
    }

    protected var mViewModel: VM? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding.lifecycleOwner = this
        onActivityCreated(savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
        mBinding.unbind()
    }

    abstract fun onActivityCreated(savedInstanceState: Bundle?)

    abstract fun getLayoutId(): Int

    fun getViewModel(clazz: Class<VM>): VM = ViewModelProvider(this).get(clazz)

    override fun getActivity(): FragmentActivity? {
        return this
    }

    fun setTransparentStatusBar() {
        BarUtils.transparentStatusBar(this)
    }

    fun setStatusBarDarkMode(boolean: Boolean) {
        BarUtils.setStatusBarLightMode(this, boolean)
    }
}