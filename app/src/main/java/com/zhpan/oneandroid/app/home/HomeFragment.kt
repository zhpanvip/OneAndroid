package com.zhpan.oneandroid.app.home

import android.view.LayoutInflater
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.zhpan.bannerview.BannerViewPager
import com.zhpan.library.base.BaseFragment
import com.zhpan.library.base.WebViewActivity
import com.zhpan.oneandroid.R
import com.zhpan.oneandroid.adapter.BannerAdapter
import com.zhpan.oneandroid.databinding.FragmentHomeBinding
import com.zhpan.oneandroid.databinding.LayoutBannerBinding
import com.zhpan.oneandroid.module.response.BannerBean
import kotlinx.android.synthetic.main.fragment_home.*

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

    private lateinit var mBannerBinding: LayoutBannerBinding;
    override fun initView() {
        setRefreshLayout(R.id.refresh_layout)
        mViewModel =
            ViewModelProvider(requireActivity(), HomeViewModelFactory(HomeRepository())).get(
                HomeViewModel::class.java
            )
        mBinding?.apply {
            adapter = articleAdapter
        }
        articleAdapter.apply {
            addHeaderView(getHeaderView())
        }
        fetchData(isRefresh = true, showLoading = true)
    }

    private fun getHeaderView(): View {
        mBannerBinding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.layout_banner,
            refresh_layout,
            false
        )
        mBannerBinding.adapter = BannerAdapter()
        mBannerBinding.pageClick = BannerViewPager.OnPageClickListener {
            var data = mBannerBinding.bannerView.getData()[it]
            if (data is BannerBean) {
                WebViewActivity.start(requireContext(), data.title!!, data.url!!)
            }
        }
        mBannerBinding.bannerView.setLifecycleRegistry(lifecycle)
        mBannerBinding.indicator = mBannerBinding.indicatorView
        return mBannerBinding.root
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

        var bannerData = mViewModel?.getBannerData(this@HomeFragment)
        bannerData
            ?.observe(viewLifecycleOwner, Observer { response ->
                run {
                    response?.let {
                        mBannerBinding.bannerView.refreshData(it)
                    }
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