package com.zhpan.library.base

import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.zhpan.library.R


/**
 * <pre>
 *   Created by zhpan on 2020/7/11.
 *   Description:
 * </pre>
 */
open class BaseBindAdapter<T>(layoutId: Int, variableId: Int) :
    BaseQuickAdapter<T, BaseBindAdapter.BindViewHolder>(layoutId) {
    private val varId = variableId
    override fun convert(helper: BindViewHolder, item: T) {
        helper.mBinding.setVariable(varId, item)
        helper.mBinding.executePendingBindings()
    }

    override fun getItemView(layoutResId: Int, parent: ViewGroup?): View {
        var binding =
            DataBindingUtil.inflate<ViewDataBinding>(mLayoutInflater, layoutResId, parent, false)
        return binding.root.apply {
            tag = R.id.BaseQuickAdapter_databinding_support
        }
    }

    class BindViewHolder(view: View) : BaseViewHolder(view) {
        val mBinding: ViewDataBinding
            get() = itemView.getTag(R.id.BaseQuickAdapter_databinding_support) as ViewDataBinding
    }
}