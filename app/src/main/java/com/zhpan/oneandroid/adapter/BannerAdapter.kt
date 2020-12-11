package com.zhpan.oneandroid.adapter

import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.zhpan.bannerview.BaseBannerAdapter
import com.zhpan.oneandroid.R
import com.zhpan.oneandroid.databinding.ItemBannerBinding
import com.zhpan.oneandroid.model.bean.BannerBean

/**
 * <pre>
 *   Created by zhpan on 2020/7/16.
 *   Description:
 * </pre>
 */
class BannerAdapter : BaseBannerAdapter<BannerBean, BannerViewHolder>() {
    override fun getLayoutId(viewType: Int): Int {
        return R.layout.item_banner
    }

    override fun createViewHolder(
        parent: ViewGroup,
        itemView: View?,
        viewType: Int
    ): BannerViewHolder {
        return BannerViewHolder(itemView!!)
    }

    override fun onBind(
        holder: BannerViewHolder?,
        data: BannerBean?,
        position: Int,
        pageSize: Int
    ) {
        val bind = DataBindingUtil.bind<ItemBannerBinding>(holder!!.itemView)
        bind?.bannerData = data
    }
}