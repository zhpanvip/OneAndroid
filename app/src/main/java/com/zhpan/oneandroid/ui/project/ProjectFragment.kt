package com.zhpan.oneandroid.ui.project

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
class ProjectFragment : BaseFragment<BaseViewModel, ViewDataBinding>() {

    companion object{
        fun getInstance(): ProjectFragment {
            return ProjectFragment()
        }
    }

    override fun fetchData() {
    }

    override fun onViewInflate() {
    }

    override fun getLayoutId(): Int {
        return R.layout.layout_article_list
    }


}