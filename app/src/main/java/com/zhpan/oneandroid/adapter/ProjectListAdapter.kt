package com.zhpan.oneandroid.adapter

import androidx.databinding.DataBindingUtil
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.zhpan.oneandroid.R
import com.zhpan.oneandroid.databinding.ItemProjectBinding
import com.zhpan.oneandroid.model.bean.ProjectBean


/**
 * <pre>
 *   Created by zhpan on 2020/7/23.
 *   Description:
 * </pre>
 */
class ProjectListAdapter(layoutId: Int = R.layout.item_project) :
    BaseQuickAdapter<ProjectBean, BaseViewHolder>(layoutId) {

    override fun onItemViewHolderCreated(viewHolder: BaseViewHolder, viewType: Int) {
        super.onItemViewHolderCreated(viewHolder, viewType)
        DataBindingUtil.bind<ItemProjectBinding>(viewHolder.itemView)
    }

    override fun convert(holder: BaseViewHolder, item: ProjectBean) {
        DataBindingUtil.getBinding<ItemProjectBinding>(holder.itemView)?.project = item
    }
}