package com.zhpan.oneandroid.adapter

import android.view.View
import com.zhpan.bannerview.BaseBannerAdapter
import com.zhpan.oneandroid.R
import com.zhpan.oneandroid.module.response.BannerBean


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

    override fun createViewHolder(itemView: View?, viewType: Int): BannerViewHolder {
        return BannerViewHolder(itemView!!)
    }


    override fun onBind(
        holder: BannerViewHolder?,
        data: BannerBean?,
        position: Int,
        pageSize: Int
    ) {
        holder?.bindData(data!!, position, pageSize)
    }


}