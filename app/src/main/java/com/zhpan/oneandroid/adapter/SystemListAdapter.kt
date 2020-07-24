package com.zhpan.oneandroid.adapter

import androidx.databinding.DataBindingUtil
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.zhpan.oneandroid.R
import com.zhpan.oneandroid.databinding.ItemSystemBinding
import com.zhpan.oneandroid.model.bean.SystemItemBean

/**
 *
 * @author zhangpan
 * @date 2020/7/24
 */
class SystemListAdapter(layoutId: Int = R.layout.item_system) :
    BaseQuickAdapter<SystemItemBean, BaseViewHolder>(layoutId) {

    override fun onItemViewHolderCreated(viewHolder: BaseViewHolder, viewType: Int) {
        super.onItemViewHolderCreated(viewHolder, viewType)
        DataBindingUtil.bind<ItemSystemBinding>(viewHolder.itemView)
    }

    override fun convert(holder: BaseViewHolder, item: SystemItemBean) {
        DataBindingUtil.getBinding<ItemSystemBinding>(holder.itemView)?.viewModel = item
    }

}