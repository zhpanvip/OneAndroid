package com.zhpan.oneandroid.ui.wechat

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayoutMediator
import com.zhpan.library.base.BaseFragment
import com.zhpan.oneandroid.R
import com.zhpan.oneandroid.adapter.WxArticleFragmentAdapter
import com.zhpan.oneandroid.databinding.FragmentOfficialAccountBinding
import com.zhpan.oneandroid.model.bean.OfficialAccountBean


/**
 * <pre>
 *   Created by zhpan on 2020/7/4.
 *   Description:
 * </pre>
 */
class OfficialAccountFragment :
    BaseFragment<OfficialAccountViewModel, FragmentOfficialAccountBinding>() {

    companion object {
        fun getInstance(): OfficialAccountFragment {
            return OfficialAccountFragment()
        }
    }

    private lateinit var adapter: WxArticleFragmentAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mViewModel = ViewModelProvider(
            requireActivity(),
            OfficialAccountViewModelFactory(OfficialAccountRepository())
        ).get(OfficialAccountViewModel::class.java)
        super.onViewCreated(view, savedInstanceState)
    }

    override fun fetchData() {
        fetchOfficialAccount(isRefresh = false, showLoading = true)
    }

    private fun fetchOfficialAccount(isRefresh: Boolean, showLoading: Boolean) {
        adapter = WxArticleFragmentAdapter(requireActivity())
        mBinding?.viewPager2?.adapter = adapter
        mViewModel?.getOificialAccountViewModel(this, showLoading)
            ?.observe(viewLifecycleOwner, object : Observer<List<OfficialAccountBean>> {
                override fun onChanged(t: List<OfficialAccountBean>?) {
                    if (t != null) {
                        adapter.officialAccountList = t
                        adapter.notifyDataSetChanged()
                        TabLayoutMediator(
                            mBinding?.tabLayout!!,
                            mBinding?.viewPager2!!
                        ) { tab, position ->
                            tab.text = t[position].name
                        }.attach()
                    }
                }
            })
    }

    override fun onViewInflate() {
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_official_account
    }
}