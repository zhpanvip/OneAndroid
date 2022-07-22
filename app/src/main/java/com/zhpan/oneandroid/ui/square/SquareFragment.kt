package com.zhpan.oneandroid.ui.square

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
 * <pre>
 *   Created by zhpan on 2020/7/4.
 *   Description:
 * </pre>
 */
class SquareFragment(override val layoutId: Int = R.layout.layout_article_list) :
  BaseFragment<SquareViewModel, LayoutArticleListBinding>() {

  companion object {
    fun getInstance(): SquareFragment {
      return SquareFragment()
    }
  }

  override fun onLazyLoad() {
    setRefreshLayout(R.id.refresh_layout)

    mBinding.apply {
      adapter = ArticleListAdapter(R.layout.item_article)
      itemClick = OnItemClickListener { adapter, _, position ->
        val data = adapter.data[position]
        if (data is ArticleBean) {
          WebViewActivity.start(requireContext(), data.title!!, data.link!!)
        }
      }
    }
    fetchData(isRefresh = true, showLoading = true)
  }

  private fun fetchData(isRefresh: Boolean, showLoading: Boolean) {
    mViewModel.getSquareArticles(page, showLoading)
    mViewModel.responseLiveData.observe(this, object : ResponseObserver<ArticleResponse>() {
      override fun onSuccess(t: ArticleResponse?) {
        ++page
        mBinding.adapter?.apply {
          if (isRefresh) {
            replaceData(t!!.datas!!)
          } else {
            addData(t!!.datas!!)
          }
          notifyDataSetChanged()
          mRefreshLayout?.finishRefresh()
          mRefreshLayout?.finishLoadMore()
        }
      }

      override fun onChanged(response: BasicResponse<ArticleResponse>?) {
        super.onChanged(response)
        mRefreshLayout?.finishRefresh()
        mRefreshLayout?.finishLoadMore()
      }

    })
  }

  override fun onRefresh(refreshLayout: RefreshLayout) {
    super.onRefresh(refreshLayout)
    fetchData(isRefresh = true, showLoading = false)
  }

  override fun onLoadMore(refreshLayout: RefreshLayout) {
    super.onLoadMore(refreshLayout)
    fetchData(isRefresh = false, showLoading = false)
  }

}