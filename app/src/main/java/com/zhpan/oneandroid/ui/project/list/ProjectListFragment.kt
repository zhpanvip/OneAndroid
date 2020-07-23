package com.zhpan.oneandroid.ui.project.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.chad.library.adapter.base.listener.OnItemClickListener
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.zhpan.library.base.BaseFragment
import com.zhpan.library.base.WebViewActivity
import com.zhpan.oneandroid.R
import com.zhpan.oneandroid.adapter.ProjectListAdapter
import com.zhpan.oneandroid.databinding.FragmentProjectListBinding
import com.zhpan.oneandroid.model.bean.ProjectBean

private const val PROJECT_CID = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ProjectListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProjectListFragment : BaseFragment<ProjectListViewModel, FragmentProjectListBinding>() {
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

    override fun fetchData() {
        fetchProjectList(isRefresh = false, showLoading = true)
    }

    private fun fetchProjectList(isRefresh: Boolean, showLoading: Boolean) {
        mViewModel?.getProjectList(this, showLoading, page, cid = projectCid!!)
            ?.observe(viewLifecycleOwner,
                Observer {
                    mRefreshLayout?.finishRefresh()
                    mRefreshLayout?.finishLoadMore()
                    if (it != null) {
                        if (isRefresh) {
                            mBinding?.adapter?.replaceData(it.datas!!)
                        } else {
                            mBinding?.adapter?.addData(it.datas!!)
                        }
                    }
                })

    }

    override fun initView() {
        setRefreshLayout(R.id.refresh_layout)
        page = 1
        mBinding?.adapter = ProjectListAdapter()
        mBinding?.itemClick = OnItemClickListener { adapter, _, position ->
            val any = adapter.data[position]
            if (any is ProjectBean) {
                WebViewActivity.start(requireContext(), any.title!!, any.link!!)
            }
        }
        mViewModel = ViewModelProvider(
            requireActivity(),
            ProjectListViewModelFactory(ProjectListRepository())
        ).get(ProjectListViewModel::class.java)
    }

    override fun onRefresh(refreshLayout: RefreshLayout) {
        super.onRefresh(refreshLayout)
        fetchProjectList(isRefresh = true, showLoading = false)
    }

    override fun onLoadMore(refreshLayout: RefreshLayout) {
        page = 1
        fetchProjectList(isRefresh = false, showLoading = false)
    }

    override fun getLayoutId(): Int = R.layout.fragment_project_list
}