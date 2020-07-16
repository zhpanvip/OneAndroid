package com.zhpan.oneandroid.app.home

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.zhpan.library.base.BaseFragment
import com.zhpan.oneandroid.R
import com.zhpan.oneandroid.databinding.FragmentHomeBinding

/**
 * <pre>
 *   Created by zhpan on 2020/7/4.
 *   Description:
 * </pre>
 */
class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding>() {

    companion object {
        fun getInstance(): HomeFragment {
            return HomeFragment()
        }
    }

    private val articleAdapter by lazy {
        HomeAdapter()
    }

    override fun initView() {
        setRefreshLayout(R.id.refresh_layout)
        mViewModel =
            ViewModelProvider(requireActivity(), HomeViewModelFactory(HomeRepository())).get(
                HomeViewModel::class.java
            )
        mBinding?.apply {
            adapter = articleAdapter
        }
        fetchData(isRefresh = true, showLoading = true)
    }

    private fun fetchData(isRefresh: Boolean, showLoading: Boolean) {
        mViewModel?.getHomeArticles(this@HomeFragment, showLoading, isRefresh)
            ?.observe(viewLifecycleOwner,
                Observer { response ->
                    mBinding?.adapter?.apply {
                        if (response != null) {
                            if (isRefresh) {
                                replaceData(response.datas)
                            } else {
                                addData(response.datas)
                            }
                            notifyDataSetChanged()
                        }
                        mRefreshLayout?.finishRefresh()
                    }
                })

    }

    override fun onViewInflate() {
    }

    override fun onRefresh(refreshLayout: RefreshLayout) {
        fetchData(isRefresh = true, showLoading = false)
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_home
    }

}