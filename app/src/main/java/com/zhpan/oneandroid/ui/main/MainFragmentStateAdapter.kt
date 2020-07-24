package com.zhpan.oneandroid.ui.main

import android.util.SparseArray
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.zhpan.library.base.BaseFragment
import com.zhpan.library.base.BaseViewModel
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

    private val fragments: SparseArray<BaseFragment<out BaseViewModel, out ViewDataBinding>> =
        SparseArray()

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment
        when (position) {
            PAGE_HOME -> {
                if (fragments.get(PAGE_HOME) == null) {
                    fragment = HomeFragment.getInstance()
                    fragments.put(PAGE_HOME, fragment)
                } else {
                    fragment = fragments.get(PAGE_HOME)
                }
            }
            PAGE_TWO -> {
                if (fragments.get(PAGE_TWO) == null) {
                    fragment = SquareFragment.getInstance()
                    fragments.put(PAGE_TWO, fragment)
                } else {
                    fragment = fragments.get(PAGE_TWO)
                }
            }

            PAGE_THREE -> {
                if (fragments.get(PAGE_THREE) == null) {
                    fragment = OfficialAccountFragment.getInstance()
                    fragments.put(PAGE_THREE, fragment)
                } else {
                    fragment = fragments.get(PAGE_THREE)
                }
            }

            PAGE_FOUR -> {
                if (fragments.get(PAGE_FOUR) == null) {
                    fragment = KnowledgeFragment.getInstance()
                    fragments.put(PAGE_FOUR, fragment)
                } else {
                    fragment = fragments.get(PAGE_FOUR)
                }
            }
            PAGE_FIVE -> {
                if (fragments.get(PAGE_FIVE) == null) {
                    fragment = ProjectFragment.getInstance()
                    fragments.put(PAGE_FIVE, fragment)
                } else {
                    fragment = fragments.get(PAGE_FIVE)
                }
            }
            else -> {
                if (fragments.get(PAGE_HOME) == null) {
                    fragment = HomeFragment.getInstance()
                    fragments.put(PAGE_HOME, fragment)
                } else {
                    fragment = fragments.get(PAGE_HOME)
                }
            }
        }
        return fragment
    }

    override fun getItemCount(): Int {
        return 5
    }

    companion object {

        const val PAGE_HOME = 0

        const val PAGE_TWO = 1

        const val PAGE_THREE = 2

        const val PAGE_FOUR = 3

        const val PAGE_FIVE = 4

    }

}
