package com.zhpan.library.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import com.scwang.smart.refresh.header.MaterialHeader
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.listener.OnRefreshListener
import com.trello.rxlifecycle2.components.support.RxFragment
import com.zhpan.library.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel

/**
 * <pre>
 *   Created by zhpan on 2020/7/5.
 *   Description:
 * </pre>
 */
abstract class BaseFragment<VM : BaseViewModel, VB : ViewDataBinding> : RxFragment(), IFragmentHost,
    OnRefreshListener,
    CoroutineScope by MainScope() {
    protected var mBinding: VB? = null
    protected var mViewModel: VM? = null
    protected var mRefreshLayout: SmartRefreshLayout? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (mBinding == null) {
            mBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
            onViewInflate()
        }

        return if (mBinding != null) {
            mBinding!!.root.apply {
                (parent as? ViewGroup)?.removeView(this)
            }
        } else {
            return super.onCreateView(inflater, container, savedInstanceState)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding?.lifecycleOwner = this
        initView()
    }

    override fun onDestroy() {
        super.onDestroy()
        cancel()
        mBinding?.unbind()

    }

    fun getViewModel(clazz: Class<VM>): VM {
        return ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(clazz)
    }

    protected open fun setRefreshLayout(@IdRes resId: Int) {
        mRefreshLayout = mBinding?.root?.findViewById<SmartRefreshLayout>(resId)
        if (needRefreshHeader()) {
            mRefreshLayout?.setRefreshHeader(getRefreshHeader())
        }
        mRefreshLayout?.setEnableLoadMore(false)
        mRefreshLayout?.setOnRefreshListener(this)
    }

    protected open fun needRefreshHeader(): Boolean {
        return true
    }

    //  获取刷新头
    protected open fun getRefreshHeader(): MaterialHeader {
        val materialHeader = MaterialHeader(context)
        materialHeader.setColorSchemeResources(R.color.design_default_color_primary)
        return materialHeader
    }

    override fun onRefresh(refreshLayout: RefreshLayout) {

    }


    abstract fun initView()

    abstract fun onViewInflate()

    abstract fun getLayoutId(): Int

}