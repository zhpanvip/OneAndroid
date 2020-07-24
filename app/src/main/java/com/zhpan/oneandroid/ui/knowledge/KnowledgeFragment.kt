package com.zhpan.oneandroid.ui.knowledge

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.chad.library.adapter.base.listener.OnItemClickListener
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.zhpan.library.base.BaseFragment
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
class KnowledgeFragment : BaseFragment<KnowledgeViewModel, LayoutSystemListBinding>() {

    companion object {
        val SYSTEM_MAP_ITEMS: String = "SYSTEM_MAP_ITEMS"

        fun getInstance(): KnowledgeFragment {
            return KnowledgeFragment()
        }
    }

    override fun fetchData() {
        fetchSystemList(true)
    }

    private fun fetchSystemList(showLoading: Boolean) {
        mViewModel?.getSystemClassify(this, showLoading)
            ?.observe(viewLifecycleOwner, Observer {
                if (it != null) {
                    mBinding?.adapter?.replaceData(it)
                }
                mRefreshLayout?.finishRefresh()
            })
    }

    override fun initView() {
        setRefreshLayout(R.id.refresh_layout)
        mRefreshLayout?.setEnableLoadMore(false)
        mViewModel = ViewModelProvider(
            requireActivity(),
            KnowledgeViewModelFactory()
        ).get(KnowledgeViewModel::class.java)
        mBinding?.adapter = SystemListAdapter()
        mBinding?.itemClick =
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

    override fun getLayoutId(): Int = R.layout.layout_system_list

    override fun onRefresh(refreshLayout: RefreshLayout) {
        super.onRefresh(refreshLayout)
        fetchSystemList(false)
    }
}