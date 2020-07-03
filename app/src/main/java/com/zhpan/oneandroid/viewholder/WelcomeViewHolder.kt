package com.zhpan.oneandroid.viewholder

import android.view.View
import android.widget.ImageView

import com.zhpan.bannerview.holder.ViewHolder
import com.zhpan.oneandroid.R


/**
 * <pre>
 * Created by zhpan on 2019/11/21.
 * Description:
</pre> *
 */
class WelcomeViewHolder : ViewHolder<Int> {
    private var mImageView: ImageView? = null

    override fun onBind(itemView: View?, data: Int?, position: Int, size: Int) {
        mImageView = itemView?.findViewById(R.id.iv_image)
        mImageView?.setImageResource(data!!)
    }

    override fun getLayoutId(): Int {
        return R.layout.item_welcome
    }

}


