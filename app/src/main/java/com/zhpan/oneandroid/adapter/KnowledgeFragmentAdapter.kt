package com.zhpan.oneandroid.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.zhpan.oneandroid.model.bean.KnowledgeBean
import com.zhpan.oneandroid.ui.knowledge.article.KnowledgeListFragment


/**
 * <pre>
 *   Created by zhpan on 2020/7/24.
 *   Description:
 * </pre>
 */
class KnowledgeFragmentAdapter(
    fragmentActivity: FragmentActivity, var list: List<KnowledgeBean>
) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int {
        return list.size
    }

    override fun createFragment(position: Int): Fragment {
        val knowledgeBean = list[position]
        return KnowledgeListFragment.newInstance(knowledgeBean.id)
    }
}