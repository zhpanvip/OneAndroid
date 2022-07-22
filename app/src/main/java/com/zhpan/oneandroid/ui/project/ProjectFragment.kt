package com.zhpan.oneandroid.ui.project

import android.os.Bundle
import android.view.View
import com.google.android.material.tabs.TabLayoutMediator
import com.zhpan.library.base.BaseFragment
import com.zhpan.library.network.ResponseObserver
import com.zhpan.oneandroid.R
import com.zhpan.oneandroid.adapter.ProjectFragmentAdapter
import com.zhpan.oneandroid.databinding.FragmentProjectBinding
import com.zhpan.oneandroid.model.bean.ProjectClassify
import kotlinx.android.synthetic.main.fragment_project.*

/**
 * <pre>
 *   Created by zhpan on 2020/7/4.
 *   Description:
 * </pre>
 */
class ProjectFragment(override val layoutId: Int = R.layout.fragment_project) :
  BaseFragment<ProjectViewModel, FragmentProjectBinding>() {
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
    mViewModel.getProjectTrees(showLoading)
    mViewModel.responseLiveData.observe(viewLifecycleOwner,
      object : ResponseObserver<List<ProjectClassify>>() {
        override fun onSuccess(data: List<ProjectClassify>?) {
          fragmentAdapter?.projectTree = data!!
          fragmentAdapter?.notifyDataSetChanged()
          tabLayout.visibility = View.VISIBLE
          TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
            tab.text = fragmentAdapter!!.projectTree[position].name
          }.attach()
        }

      })
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    fragmentAdapter = ProjectFragmentAdapter(this)
    mBinding.viewPager2.adapter = fragmentAdapter
  }
}