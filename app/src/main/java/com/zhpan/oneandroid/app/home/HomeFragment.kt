package com.zhpan.oneandroid.app.home

import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.zhpan.library.base.BaseVMFragment
import com.zhpan.oneandroid.R
import com.zhpan.oneandroid.databinding.FragmentHomeBinding
import com.zhpan.oneandroid.module.request.ArticleWrapper

/**
 * <pre>
 *   Created by zhpan on 2020/7/4.
 *   Description:
 * </pre>
 */
class HomeFragment : BaseVMFragment<HomeViewModel, FragmentHomeBinding>() {

    companion object {
        fun getInstance(): HomeFragment {
            return HomeFragment()
        }
    }

    private val articleAdapter by lazy {
        HomeAdapter()
    }

    private val mBannerViewPager by lazy {
        View(context)
    }


    override fun initView() {
        mViewModel =
            ViewModelProvider(requireActivity(), HomeViewModelFactory(HomeRepository())).get(
                HomeViewModel::class.java
            )
        mBinding?.apply {
            adapter = articleAdapter
        }
        mViewModel?.getHomeArticles(0)
            ?.observe(viewLifecycleOwner, object : Observer<ArticleWrapper> {
                override fun onChanged(t: ArticleWrapper) {
                    mBinding?.adapter?.apply {
                        addData(t.datas)
                        notifyDataSetChanged()
                    }

                }
            })
    }

    override fun onViewInflate() {
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_home
    }

}