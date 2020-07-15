package com.zhpan.oneandroid.app.home

import androidx.databinding.DataBindingUtil
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.zhpan.oneandroid.module.request.Article
import com.zhpan.oneandroid.R
import com.zhpan.oneandroid.databinding.ItemArticleBinding

/**
 * <pre>
 * Created by zhpan on 2020/7/11.
 * Description:
</pre> *
 */
internal class HomeAdapter(layoutId: Int = R.layout.item_article) :
    BaseQuickAdapter<Article, BaseViewHolder>(layoutId) {

    override fun onItemViewHolderCreated(viewHolder: BaseViewHolder, viewType: Int) {
        super.onItemViewHolderCreated(viewHolder, viewType)
        DataBindingUtil.bind<ItemArticleBinding>(viewHolder.itemView)
    }

    override fun convert(holder: BaseViewHolder, item: Article) {
        val binding = holder.getBinding<ItemArticleBinding>()
        binding?.article = item
        binding?.executePendingBindings()
    }
}