package com.zhpan.oneandroid.ui.wechat.article

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.chad.library.adapter.base.listener.OnItemClickListener
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.zhpan.library.base.BaseFragment
import com.zhpan.library.base.WebViewActivity
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
class WxArticleFragment : BaseFragment<WeChatArticleViewModel, LayoutArticleListBinding>() {
    private val articleAdapter by lazy {
        ArticleListAdapter()
    }
    private var accountId: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        if (arguments != null) {
            accountId = requireArguments().getString(OFFICIAL_ACCOUNT_ID)
        }
        mViewModel =
            ViewModelProvider(
                requireActivity(),
                WeChatArticleViewModelFactory(
                    WeChatArticleRepository()
                )
            ).get(
                WeChatArticleViewModel::class.java
            )
        super.onCreate(savedInstanceState)
    }

    override fun fetchData() {
        fetchArticles(isRefresh = false, showLoading = true)
    }

    private fun fetchArticles(isRefresh: Boolean, showLoading: Boolean) {
        mViewModel?.getWeChatViewModel(this, accountId!!, page, showLoading)
            ?.observe(viewLifecycleOwner, object : Observer<ArticleResponse> {
                override fun onChanged(t: ArticleResponse?) {
                    mRefreshLayout?.finishRefresh()
                    mRefreshLayout?.finishLoadMore()
                    if (t != null) {
                        page++
                        articleAdapter.apply {
                            if (isRefresh) {
                                replaceData(t.datas!!)
                            } else {
                                addData(t.datas!!)
                            }
                            notifyDataSetChanged()
                        }
                    }
                }

            })
    }

    override fun initView() {
        setRefreshLayout(R.id.refresh_layout)
        mBinding?.adapter = articleAdapter
        mBinding?.itemClick = OnItemClickListener { adapter, _, position ->
            run {
                val data = adapter.data[position]
                if (data is ArticleBean)
                    WebViewActivity.start(requireContext(), data.title!!, data.link!!)
            }
        }
    }

    override fun getLayoutId(): Int = R.layout.layout_article_list

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