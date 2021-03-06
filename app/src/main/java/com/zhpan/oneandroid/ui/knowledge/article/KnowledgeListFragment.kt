package com.zhpan.oneandroid.ui.knowledge.article

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.chad.library.adapter.base.listener.OnItemClickListener
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.zhpan.library.base.BaseFragment
import com.zhpan.library.base.WebViewActivity
import com.zhpan.oneandroid.R
import com.zhpan.oneandroid.adapter.ArticleListAdapter
import com.zhpan.oneandroid.base.Constants.Companion.KEY_KNOWLEDGE_CID
import com.zhpan.oneandroid.databinding.FragmentKnowledgeListBinding

class KnowledgeListFragment : BaseFragment<KnowledgeListViewModel, FragmentKnowledgeListBinding>() {
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
        mViewModel?.getKnowledgeArticles(this, showLoading, page, cid!!)
            ?.observe(viewLifecycleOwner,
                Observer {
                    it.let {
                        if (isRefresh) {
                            mBinding?.adapter?.replaceData(it.datas!!)
                        } else {
                            ++page
                            mBinding?.adapter?.addData(it.datas!!)
                        }
                        mRefreshLayout?.setNoMoreData(it.datas!!.size < DEFAULT_PAGE_SIZE)
                        mBinding?.adapter?.notifyDataSetChanged()
                    }
                    mRefreshLayout?.finishLoadMore()
                    mRefreshLayout?.finishRefresh()
                })

    }

    override fun initView() {
        mViewModel = ViewModelProvider(requireActivity(), KnowledgeListViewModelFactory()).get(
            KnowledgeListViewModel::class.java
        )
        setRefreshLayout(R.id.refresh_layout)
        mBinding?.adapter = ArticleListAdapter()
        mBinding?.itemClick =
            OnItemClickListener { _, _, position ->
                val articleBean = mBinding?.adapter!!.data[position]
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

    override fun getLayoutId(): Int = R.layout.fragment_knowledge_list
}