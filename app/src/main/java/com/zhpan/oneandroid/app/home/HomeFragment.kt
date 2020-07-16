package com.zhpan.oneandroid.app.home

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.zhpan.library.base.BaseFragment
import com.zhpan.oneandroid.R
import com.zhpan.oneandroid.databinding.FragmentHomeBinding

/**
 * <pre>
 *   Created by zhpan on 2020/7/4.
 *   Description:
 * </pre>
 */
class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding>() {

    companion object {
        fun getInstance(): HomeFragment {
            return HomeFragment()
        }
    }

    private val articleAdapter by lazy {
        HomeAdapter()
    }

    override fun initView() {
        mViewModel =
            ViewModelProvider(requireActivity(), HomeViewModelFactory(HomeRepository())).get(
                HomeViewModel::class.java
            )
        mBinding?.apply {
            adapter = articleAdapter
        }
        mViewModel?.getHomeArticles(0, this@HomeFragment, true)
            ?.observe(viewLifecycleOwner,
                Observer { response ->
                    mBinding?.adapter?.apply {
                        addData(response.datas)
                        notifyDataSetChanged()
                    }
                })
    }

    override fun onViewInflate() {
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_home
    }

}