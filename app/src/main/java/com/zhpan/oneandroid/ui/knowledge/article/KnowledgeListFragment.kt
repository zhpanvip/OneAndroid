package com.zhpan.oneandroid.ui.knowledge.article

import android.os.Bundle
import android.view.View
import com.chad.library.adapter.base.listener.OnItemClickListener
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.zhpan.library.base.BaseFragment
import com.zhpan.library.base.WebViewActivity
import com.zhpan.library.network.ResponseObserver
import com.zhpan.library.server.common.BasicResponse
import com.zhpan.oneandroid.R
import com.zhpan.oneandroid.adapter.ArticleListAdapter
import com.zhpan.oneandroid.base.Constants.Companion.KEY_KNOWLEDGE_CID
import com.zhpan.oneandroid.databinding.FragmentKnowledgeListBinding
import com.zhpan.oneandroid.model.response.ArticleResponse

class KnowledgeListFragment(override val layoutId: Int = R.layout.fragment_knowledge_list) :
  BaseFragment<KnowledgeListViewModel, FragmentKnowledgeListBinding>() {
  private var cid: String? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    arguments?.let {
      cid = it.getString(KEY_KNOWLEDGE_CID)
    }
  }

  companion object {
    @JvmStatic
    fun newInstance(cid: String) =
      KnowledgeListFragment().apply {
        arguments = Bundle().apply {
          putString(KEY_KNOWLEDGE_CID, cid)
        }
      }
  }

  override fun onLazyLoad() {
    fetchArticles(isRefresh = false, showLoading = true)
  }

  private fun fetchArticles(isRefresh: Boolean, showLoading: Boolean) {

    mViewModel.getKnowledgeArticles(showLoading, page, cid!!)
    mViewModel.articleLiveData.observe(this, object : ResponseObserver<ArticleResponse>() {
      override fun onSuccess(data: ArticleResponse?) {
        if (isRefresh) {
          mBinding.adapter?.replaceData(data?.datas!!)
        } else {
          ++page
          mBinding.adapter?.addData(data?.datas!!)
        }
        mRefreshLayout?.setNoMoreData(data?.datas!!.size < DEFAULT_PAGE_SIZE)
        mBinding.adapter?.notifyDataSetChanged()
      }

      override fun onChanged(response: BasicResponse<ArticleResponse>?) {
        super.onChanged(response)
        mRefreshLayout?.finishLoadMore()
        mRefreshLayout?.finishRefresh()
      }
    })
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    setRefreshLayout(R.id.refresh_layout)
    mBinding.adapter = ArticleListAdapter()
    mBinding.itemClick =
      OnItemClickListener { _, _, position ->
        val articleBean = mBinding.adapter!!.data[position]
        WebViewActivity.start(requireContext(), articleBean.title!!, articleBean.link!!)
      }
  }

  override fun onLoadMore(refreshLayout: RefreshLayout) {
    super.onLoadMore(refreshLayout)
    fetchArticles(isRefresh = false, showLoading = false)
  }

  override fun onRefresh(refreshLayout: RefreshLayout) {
    super.onRefresh(refreshLayout)
    fetchArticles(isRefresh = true, showLoading = false)
  }
}