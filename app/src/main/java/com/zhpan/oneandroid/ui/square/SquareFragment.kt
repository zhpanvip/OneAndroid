package com.zhpan.oneandroid.ui.square

import androidx.lifecycle.ViewModelProvider
import com.chad.library.adapter.base.listener.OnItemClickListener
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.zhpan.library.base.BaseFragment
import com.zhpan.library.base.WebViewActivity
import com.zhpan.oneandroid.R
import com.zhpan.oneandroid.adapter.ArticleListAdapter
import com.zhpan.oneandroid.base.LiveDataObserver
import com.zhpan.oneandroid.databinding.LayoutArticleListBinding
import com.zhpan.oneandroid.model.bean.Article
import com.zhpan.oneandroid.model.response.ArticleResponse

/**
 * <pre>
 *   Created by zhpan on 2020/7/4.
 *   Description:
 * </pre>
 */
class SquareFragment : BaseFragment<SquareViewModel, LayoutArticleListBinding>() {

    companion object {
        fun getInstance(): SquareFragment {
            return SquareFragment()
        }
    }

    override fun fetchData() {
        setRefreshLayout(R.id.refresh_layout)
        mViewModel =
            ViewModelProvider(requireActivity(), SquareViewModelFactory(SquareRepository())).get(
                SquareViewModel::class.java
            )
        mBinding?.apply {
            adapter = ArticleListAdapter(R.layout.item_article)
            itemClick = OnItemClickListener { adapter, _, position ->
                val data = adapter.data[position]
                if (data is Article) {
                    WebViewActivity.start(requireContext(), data.title!!, data.link!!)
                }
            }
        }
        fetchData(isRefresh = true, showLoading = true)
    }

    private fun fetchData(isRefresh: Boolean, showLoading: Boolean) {
        mViewModel?.getSquareArticles(this@SquareFragment, page, showLoading)
            ?.observe(viewLifecycleOwner, object : LiveDataObserver<ArticleResponse>() {
                override fun onSuccess(t: ArticleResponse) {
                    super.onSuccess(t)
                    ++page
                    mBinding?.adapter?.apply {
                        if (isRefresh) {
                            replaceData(t.datas!!)
                        } else {
                            addData(t.datas!!)
                        }
                        notifyDataSetChanged()
                        mRefreshLayout?.finishRefresh()
                        mRefreshLayout?.finishLoadMore()
                    }
                }

                override fun onFail(errorCode: Int?, errorMsg: String?) {
                    super.onFail(errorCode, errorMsg)
                    mRefreshLayout?.finishRefresh()
                    mRefreshLayout?.finishLoadMore()
                }
            })
    }

    override fun onRefresh(refreshLayout: RefreshLayout) {
        super.onRefresh(refreshLayout)
        fetchData(isRefresh = true, showLoading = false)
    }

    override fun onViewInflate() {
    }

    override fun getLayoutId(): Int {
        return R.layout.layout_article_list
    }

    override fun onLoadMore(refreshLayout: RefreshLayout) {
        super.onLoadMore(refreshLayout)
        fetchData(isRefresh = false, showLoading = false)
    }

}