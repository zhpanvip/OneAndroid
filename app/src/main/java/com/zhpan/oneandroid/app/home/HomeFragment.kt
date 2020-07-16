package com.zhpan.oneandroid.app.home

import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.zhpan.bannerview.BannerViewPager
import com.zhpan.bannerview.constants.PageStyle
import com.zhpan.indicator.IndicatorView
import com.zhpan.indicator.enums.IndicatorSlideMode
import com.zhpan.indicator.enums.IndicatorStyle
import com.zhpan.library.base.BaseFragment
import com.zhpan.oneandroid.R
import com.zhpan.oneandroid.adapter.BannerAdapter
import com.zhpan.oneandroid.adapter.BannerViewHolder
import com.zhpan.oneandroid.databinding.FragmentHomeBinding
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

    private lateinit var mBannerViewPager: BannerViewPager<BannerBean, BannerViewHolder>
    private lateinit var mIndicatorView: IndicatorView
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
        val headerView = LayoutInflater.from(context).inflate(
            R.layout.layout_banner,
            refresh_layout,
            false
        ) as View
        mBannerViewPager = headerView.findViewById(R.id.banner_view)
        mIndicatorView = headerView.findViewById(R.id.indicator_view)
        mBannerViewPager.apply {
            adapter = BannerAdapter()
            setIndicatorStyle(IndicatorStyle.ROUND_RECT)
            setIndicatorSlideMode(IndicatorSlideMode.SCALE)
            setAutoPlay(true)
            setScrollDuration(700)
            setIndicatorView(mIndicatorView)
            setLifecycleRegistry(lifecycle)
//            setPageMargin(resources.getDimensionPixelOffset(R.dimen.dp_15))
//            setRevealWidth(resources.getDimensionPixelOffset(R.dimen.dp_10))
            setIndicatorSliderColor(
                resources.getColor(R.color.color_slider_normal),
                resources.getColor(R.color.color_slider_checked)
            )
            setPageStyle(PageStyle.MULTI_PAGE_SCALE)
            setIndicatorSliderWidth(
                resources.getDimensionPixelOffset(R.dimen.dp_4),
                resources.getDimensionPixelOffset(R.dimen.dp_10)
            ).setIndicatorSliderGap(resources.getDimensionPixelOffset(R.dimen.dp_4)).create()

        }

        return headerView
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
                        mBannerViewPager.refreshData(it)
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