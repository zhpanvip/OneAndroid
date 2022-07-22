package com.zhpan.oneandroid.ui.wechat.article

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.chad.library.adapter.base.listener.OnItemClickListener
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.zhpan.library.base.BaseFragment
import com.zhpan.library.base.WebViewActivity
import com.zhpan.library.network.ResponseObserver
import com.zhpan.library.server.common.BasicResponse
import com.zhpan.oneandroid.R
import com.zhpan.oneandroid.adapter.ArticleListAdapter
import com.zhpan.oneandroid.databinding.LayoutArticleListBinding
import com.zhpan.oneandroid.model.bean.ArticleBean
import com.zhpan.oneandroid.model.response.ArticleResponse

/**
 * A simple [Fragment] subclass.
 * Use the [WxArticleFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class WxArticleFragment(override val layoutId: Int = R.layout.layout_article_list) :
  BaseFragment<WeChatArticleViewModel, LayoutArticleListBinding>() {
  private val articleAdapter by lazy {
    ArticleListAdapter()
  }
  private var accountId: String? = null
  override fun onCreate(savedInstanceState: Bundle?) {
    if (arguments != null) {
      accountId = requireArguments().getString(OFFICIAL_ACCOUNT_ID)
    }
    super.onCreate(savedInstanceState)
  }

  override fun onLazyLoad() {
    fetchArticles(isRefresh = false, showLoading = true)
  }

  private fun fetchArticles(isRefresh: Boolean, showLoading: Boolean) {
    mViewModel.getWeChatViewModel(accountId!!, page, showLoading)
    mViewModel.responseLiveData.observe(this, object : ResponseObserver<ArticleResponse>() {
      override fun onSuccess(data: ArticleResponse?) {
        if (data != null) {
          page++
          articleAdapter.apply {
            if (isRefresh) {
              replaceData(data.datas!!)
            } else {
              addData(data.datas!!)
            }
            notifyDataSetChanged()
          }
        }
      }

      override fun onChanged(response: BasicResponse<ArticleResponse>?) {
        super.onChanged(response)
        mRefreshLayout?.finishRefresh()
        mRefreshLayout?.finishLoadMore()
      }
    })
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    setRefreshLayout(R.id.refresh_layout)
    mBinding.adapter = articleAdapter
    mBinding.itemClick = OnItemClickListener { adapter, _, position ->
      run {
        val data = adapter.data[position]
        if (data is ArticleBean)
          WebViewActivity.start(requireContext(), data.title!!, data.link!!)
      }
    }
  }

  companion object {
    private const val OFFICIAL_ACCOUNT_ID = "OFFICIAL_ACCOUNT_ID"
    fun newInstance(accountId: String?): WxArticleFragment {
      val fragment =
        WxArticleFragment()
      val args = Bundle()
      args.putString(OFFICIAL_ACCOUNT_ID, accountId)
      fragment.arguments = args
      return fragment
    }
  }

  override fun onRefresh(refreshLayout: RefreshLayout) {
    super.onRefresh(refreshLayout)
    fetchArticles(isRefresh = true, showLoading = false)
  }

  override fun onLoadMore(refreshLayout: RefreshLayout) {
    super.onLoadMore(refreshLayout)
    fetchArticles(isRefresh = false, showLoading = false)
  }
}