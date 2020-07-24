package com.zhpan.library.base

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
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
}