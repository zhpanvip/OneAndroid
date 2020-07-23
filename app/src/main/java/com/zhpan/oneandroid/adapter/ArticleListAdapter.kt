package com.zhpan.oneandroid.adapter

import androidx.databinding.DataBindingUtil
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.zhpan.oneandroid.model.bean.ArticleBean
import com.zhpan.oneandroid.R
import com.zhpan.oneandroid.databinding.ItemArticleBinding

/**
 * <pre>
 * Created by zhpan on 2020/7/11.
 * Description:
</pre> *
 */
internal class ArticleListAdapter(layoutId: Int = R.layout.item_article) :
    BaseQuickAdapter<ArticleBean, BaseViewHolder>(layoutId) {

    override fun onItemViewHolderCreated(viewHolder: BaseViewHolder, viewType: Int) {
        super.onItemViewHolderCreated(viewHolder, viewType)
        DataBindingUtil.bind<ItemArticleBinding>(viewHolder.itemView)
    }

    override fun convert(holder: BaseViewHolder, item: ArticleBean) {
        DataBindingUtil.getBinding<ItemArticleBinding>(holder.itemView)?.article = item
    }
}