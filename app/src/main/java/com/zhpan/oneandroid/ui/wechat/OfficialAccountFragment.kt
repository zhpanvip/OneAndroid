package com.zhpan.oneandroid.ui.wechat

import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.zhpan.library.base.BaseFragment
import com.zhpan.oneandroid.R
import com.zhpan.oneandroid.model.bean.OfficialAccountBean


/**
 * <pre>
 *   Created by zhpan on 2020/7/4.
 *   Description:
 * </pre>
 */
class OfficialAccountFragment : BaseFragment<OfficialAccountViewModel, ViewDataBinding>() {

    companion object {
        fun getInstance(): OfficialAccountFragment {
            return OfficialAccountFragment()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mViewModel = ViewModelProvider(
            requireActivity(),
            OfficialAccountViewModelFactory(OfficialAccountRepository())
        ).get(OfficialAccountViewModel::class.java)
        super.onViewCreated(view, savedInstanceState)
    }

    override fun fetchData() {
        fetchOfficialAccount(false, true)
    }

    private fun fetchOfficialAccount(isRefresh: Boolean, showLoading: Boolean) {
        mViewModel?.getOificialAccountViewModel(this, showLoading)
            ?.observe(viewLifecycleOwner, object : Observer<List<OfficialAccountBean>> {
                override fun onChanged(t: List<OfficialAccountBean>?) {
                    if (t != null) {

                    }
                }

            })
    }

    override fun onViewInflate() {
    }

    override fun getLayoutId(): Int = R.layout.fragment_wx_official_account
}