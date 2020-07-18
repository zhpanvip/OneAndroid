package com.zhpan.oneandroid.ui.square

import androidx.lifecycle.ViewModelProvider
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.zhpan.library.base.BaseFragment
import com.zhpan.oneandroid.R
import com.zhpan.oneandroid.adapter.ArticleListAdapter
import com.zhpan.oneandroid.base.LiveDataObserver
import com.zhpan.oneandroid.databinding.LayoutArticleListBinding
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
        mBinding?.adapter = ArticleListAdapter(R.layout.item_article)
        fetchData(isRefresh = true, showLoading = true)
    }

    private fun fetchData(isRefresh: Boolean, showLoading: Boolean) {
        mViewModel?.getSquareArticles(this@SquareFragment, page, showLoading)
            ?.observe(viewLifecycleOwner, object : LiveDataObserver<ArticleResponse>() {
                override fun onSuccess(t: ArticleResponse) {
                    super.onSuccess(t)
                    ++page
                    mBinding?.adapter?.apply {
                        replaceData(t.datas!!)
                        notifyDataSetChanged()
                        mRefreshLayout?.finishRefresh()
                    }
                }

                override fun onFail(errorCode: Int?, errorMsg: String?) {
                    super.onFail(errorCode, errorMsg)
                    mRefreshLayout?.finishRefresh()
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


}