package com.zhpan.oneandroid.ui.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.zhpan.oneandroid.ui.home.HomeFragment
import com.zhpan.oneandroid.ui.project.ProjectFragment
import com.zhpan.oneandroid.ui.square.SquareFragment
import com.zhpan.oneandroid.ui.knowledge.KnowledgeFragment
import com.zhpan.oneandroid.ui.wechat.OfficialAccountFragment

/**
 * <pre>
 * Created by zhangpan on 2019-12-05.
 * Description:
 *</pre>
 */
class MainFragmentStateAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            PAGE_TWO -> SquareFragment.getInstance()
            PAGE_THREE -> OfficialAccountFragment.getInstance()
            PAGE_FOUR -> KnowledgeFragment.getInstance()
            PAGE_FIVE -> ProjectFragment.getInstance()
            else -> HomeFragment.getInstance()
        }
    }

    override fun getItemCount(): Int {
        return 5
    }

    companion object {
        const val PAGE_TWO = 1
        const val PAGE_THREE = 2
        const val PAGE_FOUR = 3
        const val PAGE_FIVE = 4
    }
}
