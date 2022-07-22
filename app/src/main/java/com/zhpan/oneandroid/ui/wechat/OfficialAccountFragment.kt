package com.zhpan.oneandroid.ui.wechat

import com.google.android.material.tabs.TabLayoutMediator
import com.zhpan.library.base.BaseFragment
import com.zhpan.library.network.ResponseObserver
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
class OfficialAccountFragment(override val layoutId: Int = R.layout.fragment_official_account) :
  BaseFragment<OfficialAccountViewModel, FragmentOfficialAccountBinding>() {

  companion object {
    fun getInstance(): OfficialAccountFragment {
      return OfficialAccountFragment()
    }
  }

  private lateinit var adapter: WxArticleFragmentAdapter

  override fun onLazyLoad() {
    fetchOfficialAccount(isRefresh = false, showLoading = true)
  }

  private fun fetchOfficialAccount(isRefresh: Boolean, showLoading: Boolean) {
    adapter = WxArticleFragmentAdapter(requireActivity())
    mBinding.viewPager2.adapter = adapter
    mViewModel.getOfficialAccountViewModel(showLoading)
    mViewModel.responseLiveData.observe(viewLifecycleOwner,
      object : ResponseObserver<List<OfficialAccountBean>>() {
        override fun onSuccess(data: List<OfficialAccountBean>?) {
          if (data != null) {
            adapter.officialAccountList = data
            adapter.notifyDataSetChanged()
            TabLayoutMediator(
              mBinding.tabLayout,
              mBinding.viewPager2
            ) { tab, position ->
              tab.text = data[position].name
            }.attach()
          }
        }

      })
  }
}