package com.zhpan.library.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import com.scwang.smart.refresh.footer.BallPulseFooter
import com.scwang.smart.refresh.header.MaterialHeader
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import com.scwang.smart.refresh.layout.api.RefreshFooter
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.constant.SpinnerStyle
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener
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
    OnLoadMoreListener,
    CoroutineScope by MainScope() {
    protected var mBinding: VB? = null
    protected var mViewModel: VM? = null
    protected var mRefreshLayout: SmartRefreshLayout? = null
    protected var page: Int = 0
    private var isDataLoaded: Boolean = false

    companion object {
        const val DEFAULT_PAGE_SIZE = 20
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (mBinding == null) {
            mBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
            initView()
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
    }

    override fun onResume() {
        super.onResume()
        // 处理懒加载
        if (!isDataLoaded) {
            isDataLoaded = true
            onLazyLoad()
        }
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
            mRefreshLayout?.setRefreshFooter(getRefreshFooter())
        }
        mRefreshLayout?.setEnableLoadMore(true)
        mRefreshLayout?.setOnRefreshListener(this)
        mRefreshLayout?.setOnLoadMoreListener(this)
    }

    private fun getRefreshFooter(): RefreshFooter {
        val ballFooter =
            BallPulseFooter(requireContext()).setSpinnerStyle(SpinnerStyle.FixedBehind)
        ballFooter.setAnimatingColor(requireContext().resources.getColor(R.color.colorPrimary))
        return ballFooter
    }

    protected open fun needRefreshHeader(): Boolean {
        return true
    }

    //  获取刷新头
    protected open fun getRefreshHeader(): MaterialHeader {
        val materialHeader = MaterialHeader(context)
        materialHeader.setColorSchemeResources(R.color.colorPrimaryDark)
        return materialHeader
    }

    override fun onRefresh(refreshLayout: RefreshLayout) {
        page = 0
    }

    override fun onLoadMore(refreshLayout: RefreshLayout) {

    }

    abstract fun onLazyLoad()

    abstract fun initView()

    abstract fun getLayoutId(): Int

}