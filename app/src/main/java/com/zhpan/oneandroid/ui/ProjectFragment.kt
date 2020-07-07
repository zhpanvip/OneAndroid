package com.zhpan.oneandroid.ui

import androidx.databinding.ViewDataBinding
import com.zhpan.library.base.BaseVMFragment
import com.zhpan.library.base.BaseViewModel
import com.zhpan.oneandroid.R


/**
 * <pre>
 *   Created by zhpan on 2020/7/4.
 *   Description:
 * </pre>
 */
class ProjectFragment : BaseVMFragment<BaseViewModel, ViewDataBinding>() {

    companion object{
        fun getInstance(): ProjectFragment {
            return ProjectFragment()
        }
    }

    override fun initFragment() {
    }

    override fun onViewInflate() {
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_home
    }


}