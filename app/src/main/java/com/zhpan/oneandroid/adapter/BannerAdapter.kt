package com.zhpan.oneandroid.adapter

import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.zhpan.bannerview.BaseBannerAdapter
import com.zhpan.bannerview.BaseViewHolder
import com.zhpan.bannerview.utils.BannerUtils
import com.zhpan.library.view.CornerImageView
import com.zhpan.oneandroid.R
import com.zhpan.oneandroid.databinding.ItemBannerBinding
import com.zhpan.oneandroid.model.bean.BannerBean

/**
 * <pre>
 *   Created by zhpan on 2020/7/16.
 *   Description:
 * </pre>
 */
class BannerAdapter : BaseBannerAdapter<BannerBean>() {
    override fun getLayoutId(viewType: Int): Int {
        return R.layout.item_banner
    }

    override fun bindData(
        holder: BaseViewHolder<BannerBean>?,
        data: BannerBean?,
        position: Int,
        pageSize: Int
    ) {
        val imageView = holder!!.findViewById<CornerImageView>(R.id.banner_image)
        imageView.setRoundCorner(BannerUtils.dp2px(0f))
        Glide.with(imageView).load(data?.imagePath).into(imageView)
        val bind = DataBindingUtil.bind<ItemBannerBinding>(holder.itemView)
        bind?.bannerData = data
    }
}