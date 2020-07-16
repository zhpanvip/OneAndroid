package com.zhpan.oneandroid.ui

import androidx.databinding.ViewDataBinding
import com.zhpan.library.base.BaseFragment
import com.zhpan.library.base.BaseViewModel
import com.zhpan.oneandroid.R


/**
 * <pre>
 *   Created by zhpan on 2020/7/4.
 *   Description:
 * </pre>
 */
class SystemFragment : BaseFragment<BaseViewModel, ViewDataBinding>() {

    companion object {
        fun getInstance(): SystemFragment {
            return SystemFragment()
        }
    }

    override fun initView() {
    }

    override fun onViewInflate() {
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_home
    }


}