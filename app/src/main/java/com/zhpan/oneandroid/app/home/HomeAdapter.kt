package com.zhpan.oneandroid.app.home

import com.zhpan.library.base.BaseBindAdapter
import com.zhpan.oneandroid.module.request.Article
import com.zhpan.oneandroid.BR
import com.zhpan.oneandroid.R

/**
 * <pre>
 * Created by zhpan on 2020/7/11.
 * Description:
</pre> *
 */
internal class HomeAdapter(layoutId: Int = R.layout.item_article) :
    BaseBindAdapter<Article>(layoutId, BR.article) {

    override fun convert(helper: BindViewHolder, item: Article) {
        super.convert(helper, item)
        helper.setText(R.id.tv_title,item.title)
    }
}