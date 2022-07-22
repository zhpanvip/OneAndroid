package com.zhpan.oneandroid.ui.knowledge

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.chad.library.adapter.base.listener.OnItemClickListener
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.zhpan.library.base.BaseFragment
import com.zhpan.library.network.ResponseObserver
import com.zhpan.library.server.common.BasicResponse
import com.zhpan.oneandroid.R
import com.zhpan.oneandroid.adapter.SystemListAdapter
import com.zhpan.oneandroid.base.Constants
import com.zhpan.oneandroid.databinding.LayoutSystemListBinding
import com.zhpan.oneandroid.model.bean.KnowledgeBean
import com.zhpan.oneandroid.ui.knowledge.article.KnowledgeActivity
import java.io.Serializable

/**
 * <pre>
 *   Created by zhpan on 2020/7/4.
 *   Description:
 * </pre>
 */
class KnowledgeFragment(override val layoutId: Int = R.layout.layout_system_list) :
  BaseFragment<KnowledgeViewModel, LayoutSystemListBinding>() {

  companion object {
    const val SYSTEM_MAP_ITEMS: String = "SYSTEM_MAP_ITEMS"

    fun getInstance(): KnowledgeFragment {
      return KnowledgeFragment()
    }
  }

  override fun onLazyLoad() {
    fetchSystemList(true)
  }

  private fun fetchSystemList(showLoading: Boolean) {
    mViewModel.getSystemClassify(showLoading)
    mViewModel.responseLiveData.observe(this, object : ResponseObserver<List<KnowledgeBean>>() {
      override fun onSuccess(data: List<KnowledgeBean>?) {
        if (data != null) {
          mBinding.adapter?.replaceData(data)
        }
      }

      override fun onChanged(response: BasicResponse<List<KnowledgeBean>>?) {
        super.onChanged(response)
        mRefreshLayout?.finishRefresh()
      }
    })
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    setRefreshLayout(R.id.refresh_layout)
    mRefreshLayout?.setEnableLoadMore(false)

    mBinding.adapter = SystemListAdapter()
    mBinding.itemClick =
      OnItemClickListener { adapter, _, position ->
        val data = adapter.data[position]
        if (data is KnowledgeBean) {
          val intent = Intent(activity, KnowledgeActivity::class.java)
          val bundle = Bundle()
          bundle.putString(Constants.KEY_KNOWLEDGE_TITLE, data.name)
          bundle.putSerializable(SYSTEM_MAP_ITEMS, (data.children as Serializable))
          intent.putExtra(SYSTEM_MAP_ITEMS, bundle)
          startActivity(intent)
        }

      }
  }

  override fun onRefresh(refreshLayout: RefreshLayout) {
    super.onRefresh(refreshLayout)
    fetchSystemList(false)
  }
}