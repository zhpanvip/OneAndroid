package com.zhpan.oneandroid.ui.project

import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.zhpan.library.base.BaseFragment
import com.zhpan.library.base.BaseViewModel
import com.zhpan.oneandroid.R
import com.zhpan.oneandroid.adapter.ProjectFragmentAdapter
import com.zhpan.oneandroid.databinding.FragmentProjectBinding
import kotlinx.android.synthetic.main.fragment_project.*


/**
 * <pre>
 *   Created by zhpan on 2020/7/4.
 *   Description:
 * </pre>
 */
class ProjectFragment : BaseFragment<ProjectViewModel, FragmentProjectBinding>() {
    private var fragmentAdapter: ProjectFragmentAdapter? = null

    companion object {
        fun getInstance(): ProjectFragment {
            return ProjectFragment()
        }
    }

    override fun onLazyLoad() {
        fetchProjectTrees(isRefresh = false, showLoading = true)
    }

    private fun fetchProjectTrees(isRefresh: Boolean, showLoading: Boolean) {
        mViewModel?.getProjectTrees(this, showLoading)?.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                fragmentAdapter?.projectTree = it
                fragmentAdapter?.notifyDataSetChanged()
                tabLayout.visibility = View.VISIBLE
                TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
                    tab.text = fragmentAdapter!!.projectTree[position].name
                }.attach()
            }
        })
    }

    override fun initView() {
        fragmentAdapter = ProjectFragmentAdapter(this)
        mBinding?.viewPager2?.adapter = fragmentAdapter
        mViewModel =
            ViewModelProvider(requireActivity(), ProjectViewModelFactory(ProjectRepository())).get(
                ProjectViewModel::class.java
            )
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_project
    }
}