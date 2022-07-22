package com.zhpan.oneandroid.ui.project.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.chad.library.adapter.base.listener.OnItemClickListener
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.zhpan.library.base.BaseFragment
import com.zhpan.library.base.WebViewActivity
import com.zhpan.library.network.ResponseObserver
import com.zhpan.library.server.common.BasicResponse
import com.zhpan.oneandroid.R
import com.zhpan.oneandroid.adapter.ProjectListAdapter
import com.zhpan.oneandroid.databinding.FragmentProjectListBinding
import com.zhpan.oneandroid.model.bean.ProjectBean
import com.zhpan.oneandroid.model.response.ProjectResponse

private const val PROJECT_CID = "param1"

/**
 * A simple [Fragment] subclass.
 * Use the [ProjectListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProjectListFragment(override val layoutId: Int = R.layout.fragment_project_list) :
  BaseFragment<ProjectListViewModel, FragmentProjectListBinding>() {
  private var projectCid: String? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    arguments?.let {
      projectCid = it.getString(PROJECT_CID)
    }
  }

  companion object {
    @JvmStatic
    fun newInstance(projectCid: String) =
      ProjectListFragment().apply {
        arguments = Bundle().apply {
          putString(PROJECT_CID, projectCid)
        }
      }
  }

  override fun onLazyLoad() {
    fetchProjectList(isRefresh = false, showLoading = true)
  }

  private fun fetchProjectList(isRefresh: Boolean, showLoading: Boolean) {
    mViewModel.getProjectList(showLoading, page, cid = projectCid!!)
    mViewModel.responseLiveData.observe(viewLifecycleOwner,
      object : ResponseObserver<ProjectResponse>() {
        override fun onSuccess(data: ProjectResponse?) {

          if (data != null) {
            if (isRefresh) {
              mBinding.adapter?.replaceData(data.datas!!)
            } else {
              mBinding.adapter?.addData(data.datas!!)
            }
            mRefreshLayout?.setNoMoreData(data.datas?.size!! < DEFAULT_PAGE_SIZE)
          }
        }

        override fun onChanged(response: BasicResponse<ProjectResponse>?) {
          super.onChanged(response)
          mRefreshLayout?.finishRefresh()
          mRefreshLayout?.finishLoadMore()
        }
      })
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    setRefreshLayout(R.id.refresh_layout)
    page = 1
    mBinding.adapter = ProjectListAdapter()
    mBinding.itemClick = OnItemClickListener { adapter, _, position ->
      val any = adapter.data[position]
      if (any is ProjectBean) {
        WebViewActivity.start(requireContext(), any.title!!, any.link!!)
      }
    }
  }

  override fun onRefresh(refreshLayout: RefreshLayout) {
    super.onRefresh(refreshLayout)
    fetchProjectList(isRefresh = true, showLoading = false)
  }

  override fun onLoadMore(refreshLayout: RefreshLayout) {
    page = 1
    fetchProjectList(isRefresh = false, showLoading = false)
  }
}