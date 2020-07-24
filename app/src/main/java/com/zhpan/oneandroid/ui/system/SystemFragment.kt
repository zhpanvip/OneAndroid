package com.zhpan.oneandroid.ui.system

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.zhpan.library.base.BaseFragment
import com.zhpan.oneandroid.R
import com.zhpan.oneandroid.adapter.SystemListAdapter
import com.zhpan.oneandroid.databinding.LayoutSystemListBinding


/**
 * <pre>
 *   Created by zhpan on 2020/7/4.
 *   Description:
 * </pre>
 */
class SystemFragment : BaseFragment<SystemViewModel, LayoutSystemListBinding>() {

    companion object {
        fun getInstance(): SystemFragment {
            return SystemFragment()
        }
    }

    override fun fetchData() {
        fetchSystemList(true)
    }

    private fun fetchSystemList(showLoading: Boolean) {
        mViewModel?.getSystemClassify(this, showLoading)
            ?.observe(viewLifecycleOwner, Observer {
                if (it != null) {
                    mBinding?.adapter?.replaceData(it)
                }
                mRefreshLayout?.finishRefresh()
            })
    }

    override fun initView() {
        setRefreshLayout(R.id.refresh_layout)
        mRefreshLayout?.setEnableLoadMore(false)
        mViewModel = ViewModelProvider(
            requireActivity(),
            SystemViewModelFactory()
        ).get(SystemViewModel::class.java)
        mBinding?.adapter = SystemListAdapter()
    }

    override fun getLayoutId(): Int = R.layout.layout_system_list

    override fun onRefresh(refreshLayout: RefreshLayout) {
        super.onRefresh(refreshLayout)
        fetchSystemList(false)
    }
}