package com.zhpan.oneandroid.adapter

import android.view.View
import com.bumptech.glide.Glide
import com.zhpan.bannerview.BaseViewHolder
import com.zhpan.bannerview.utils.BannerUtils
import com.zhpan.library.view.CornerImageView
import com.zhpan.oneandroid.R
import com.zhpan.oneandroid.module.response.BannerBean

/**
 * <pre>
 * Created by zhangpan on 2019-08-14.
 * Description:
</pre> *
 */
class BannerViewHolder(itemView: View) :
    BaseViewHolder<BannerBean>(itemView) {
    override fun bindData(data: BannerBean, position: Int, pageSize: Int) {
        val imageView = findView<CornerImageView>(R.id.banner_image)
        Glide.with(imageView).load(data.imagePath).into(imageView)
    }

    init {
        val imageView = findView<CornerImageView>(R.id.banner_image)
        imageView.setRoundCorner(BannerUtils.dp2px(0f))
    }
}