package com.zhpan.oneandroid.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.blankj.utilcode.util.LogUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnItemClickListener
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.zhpan.bannerview.BannerViewPager
import com.zhpan.library.base.BaseFragment
import com.zhpan.library.base.WebViewActivity
import com.zhpan.oneandroid.R
import com.zhpan.oneandroid.adapter.ArticleListAdapter
import com.zhpan.oneandroid.adapter.BannerAdapter
import com.zhpan.oneandroid.databinding.LayoutArticleListBinding
import com.zhpan.oneandroid.databinding.LayoutBannerBinding
import com.zhpan.oneandroid.model.bean.Article
import com.zhpan.oneandroid.model.bean.BannerBean
import kotlinx.android.synthetic.main.layout_article_list.*

/**
 * <pre>
 *   Created by zhpan on 2020/7/4.
 *   Description:
 * </pre>
 */
class HomeFragment : BaseFragment<HomeViewModel, LayoutArticleListBinding>() {

    companion object {
        fun getInstance(): HomeFragment {
            return HomeFragment()
        }
    }

    private val articleAdapter by lazy {
        ArticleListAdapter()
    }

    private var mBannerBinding: LayoutBannerBinding? = null
    override fun fetchData() {
        setRefreshLayout(R.id.refresh_layout)
        mViewModel =
            ViewModelProvider(requireActivity(), HomeViewModelFactory(HomeRepository())).get(
                HomeViewModel::class.java
            )
        mBinding?.apply {
            adapter = articleAdapter
            itemClick = OnItemClickListener { adapter, _, position ->
                val data = adapter.data[position]
                if (data is Article) {
                    WebViewActivity.start(requireContext(), data.title!!, data.link!!)
                }
            }
        }

        if (mBannerBinding == null) {
            articleAdapter.addHeaderView(getHeaderView())
        }

        fetchData(isRefresh = false, showLoading = true)
    }

    private fun getHeaderView(): View {
        mBannerBinding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.layout_banner,
            refresh_layout,
            false
        )
        mBannerBinding?.adapter = BannerAdapter()
        mBannerBinding?.pageClick = BannerViewPager.OnPageClickListener {
            val data = mBannerBinding!!.bannerView.getData()[it]
            if (data is BannerBean) {
                WebViewActivity.start(requireContext(), data.title!!, data.url!!)
            }
        }
        mBannerBinding?.bannerView?.setLifecycleRegistry(lifecycle)
        mBannerBinding?.indicator = mBannerBinding?.indicatorView
        return mBannerBinding!!.root
    }

    private fun fetchData(isRefresh: Boolean, showLoading: Boolean) {
        mViewModel?.getHomeArticles(this@HomeFragment, page, showLoading)
            ?.observe(viewLifecycleOwner,
                Observer { response ->
                    mBinding?.adapter?.apply {
                        if (response != null) {
                            ++page
                            if (isRefresh) {
                                replaceData(response.datas!!)
                            } else {
                                addData(response.datas!!)
                            }
                            notifyDataSetChanged()
                        }
                        mRefreshLayout?.finishRefresh()
                        mRefreshLayout?.finishLoadMore()
                    }
                })

        val bannerData = mViewModel?.getBannerData(this@HomeFragment)
        bannerData
            ?.observe(viewLifecycleOwner, Observer { response ->
                run {
                    response?.let {
                        mBannerBinding?.bannerView?.refreshData(it)
                    }
                }
            })
    }

    override fun onViewInflate() {
    }

    override fun onRefresh(refreshLayout: RefreshLayout) {
        super.onRefresh(refreshLayout)
        fetchData(isRefresh = true, showLoading = false)
    }

    override fun getLayoutId(): Int {
        return R.layout.layout_article_list
    }

    private val TAG: String = "HOME_FRAGMENT"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LogUtils.e(TAG, "onCreate")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        LogUtils.e(TAG, "onCreateView")
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        LogUtils.e(TAG, "onResume")
    }

    override fun onStart() {
        super.onStart()
        LogUtils.e(TAG, "onStart")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        LogUtils.e(TAG, "onViewCreated")
    }

    override fun onLoadMore(refreshLayout: RefreshLayout) {
        super.onLoadMore(refreshLayout)
        fetchData(isRefresh = false, showLoading = false)
    }

}