package com.zhpan.oneandroid.ui.home

import android.view.LayoutInflater
import android.view.View
import androidx.databinding.DataBindingUtil
import com.chad.library.adapter.base.listener.OnItemClickListener
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.zhpan.bannerview.BannerViewPager
import com.zhpan.library.base.NewBaseFragment
import com.zhpan.library.base.WebViewActivity
import com.zhpan.library.network.ResponseObserver
import com.zhpan.oneandroid.R
import com.zhpan.oneandroid.adapter.ArticleListAdapter
import com.zhpan.oneandroid.adapter.BannerAdapter
import com.zhpan.oneandroid.databinding.LayoutArticleListBinding
import com.zhpan.oneandroid.databinding.LayoutBannerBinding
import com.zhpan.oneandroid.model.bean.ArticleBean
import com.zhpan.oneandroid.model.bean.BannerBean
import com.zhpan.oneandroid.model.response.ArticleResponse
import kotlinx.android.synthetic.main.layout_article_list.*

/**
 * <pre>
 *   Created by zhpan on 2020/7/4.
 *   Description:
 * </pre>
 */
class HomeFragment(override val layoutId: Int = R.layout.layout_article_list) :
  NewBaseFragment<HomeViewModel, LayoutArticleListBinding>() {

  companion object {
    fun getInstance(): HomeFragment {
      return HomeFragment()
    }
  }

  private val articleAdapter by lazy {
    ArticleListAdapter()
  }

  private var mBannerBinding: LayoutBannerBinding? = null
  override fun onLazyLoad() {
    setRefreshLayout(R.id.refresh_layout)
    mBinding.apply {
      adapter = articleAdapter
      itemClick = OnItemClickListener { adapter, _, position ->
        val data = adapter.data[position]
        if (data is ArticleBean) {
          WebViewActivity.start(requireContext(), data.title!!, data.link!!)
        }
      }
    }

    if (mBannerBinding == null) {
      articleAdapter.addHeaderView(getHeaderView())
    }

    fetchArticles(isRefresh = false, showLoading = true)
    fetchBannerData()
  }

  private fun fetchBannerData() {
    mViewModel.getBannerData()

    mViewModel.bannerLiveData.observe(
      viewLifecycleOwner,
      object : ResponseObserver<List<BannerBean>>() {
        override fun onSuccess(data: List<BannerBean>?) {
          mBannerBinding?.bannerView?.refreshData(data)
        }
      })
  }

  private fun getHeaderView(): View {
    mBannerBinding = DataBindingUtil.inflate(
      LayoutInflater.from(context),
      R.layout.layout_banner,
      refresh_layout,
      false
    )
    mBannerBinding?.adapter = BannerAdapter()
    mBannerBinding?.pageClick = BannerViewPager.OnPageClickListener { _, position ->
      val data = mBannerBinding!!.bannerView.getData()[position]
      if (data is BannerBean) {
        WebViewActivity.start(requireContext(), data.title!!, data.url!!)
      }
    }
    mBannerBinding?.bannerView?.setLifecycleRegistry(lifecycle)
    mBannerBinding?.indicator = mBannerBinding?.indicatorView
    return mBannerBinding!!.root
  }

  private fun fetchArticles(isRefresh: Boolean, showLoading: Boolean) {
    mViewModel.getHomeArticles(page, showLoading)

    mViewModel.articleLiveData.observe(this, object : ResponseObserver<ArticleResponse>() {
      override fun onSuccess(data: ArticleResponse?) {
        mBinding.adapter?.apply {
          if (data != null) {
            ++page
            if (isRefresh) {
              replaceData(data.datas!!)
            } else {
              addData(data.datas!!)
            }
            notifyDataSetChanged()
          }
          mRefreshLayout?.finishRefresh()
          mRefreshLayout?.finishLoadMore()
        }
      }
    })
  }

  override fun onRefresh(refreshLayout: RefreshLayout) {
    super.onRefresh(refreshLayout)
    fetchArticles(isRefresh = true, showLoading = false)
    fetchBannerData()
  }

  override fun onLoadMore(refreshLayout: RefreshLayout) {
    super.onLoadMore(refreshLayout)
    fetchArticles(isRefresh = false, showLoading = false)
  }
}