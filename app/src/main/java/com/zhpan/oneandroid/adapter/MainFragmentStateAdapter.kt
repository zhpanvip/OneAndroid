package com.zhpan.oneandroid.adapter

import android.util.SparseArray
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.zhpan.library.base.BaseVMFragment
import com.zhpan.library.base.BaseViewModel
import com.zhpan.oneandroid.ui.*

/**
 * <pre>
 * Created by zhangpan on 2019-12-05.
 * Description:
 *</pre>
 */
class MainFragmentStateAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {

    private val fragments: SparseArray<BaseVMFragment<in BaseViewModel, in ViewDataBinding>> =
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
            PAGE_FIND -> {
                if (fragments.get(PAGE_FIND) == null) {
                    fragment = SquareFragment.getInstance()
                    fragments.put(PAGE_FIND, fragment)
                } else {
                    fragment = fragments.get(PAGE_FIND)
                }
            }

            PAGE_INDICATOR -> {
                if (fragments.get(PAGE_INDICATOR) == null) {
                    fragment = WechatFragment.getInstance()
                    fragments.put(PAGE_INDICATOR, fragment)
                } else {
                    fragment = fragments.get(PAGE_INDICATOR)
                }
            }

            PAGE_OTHERS -> {
                if (fragments.get(PAGE_OTHERS) == null) {
                    fragment = SystemFragment.getInstance()
                    fragments.put(PAGE_OTHERS, fragment)
                } else {
                    fragment = fragments.get(PAGE_OTHERS)
                }
            }
            PAGE_PROJECT -> {
                if (fragments.get(PAGE_PROJECT) == null) {
                    fragment = ProjectFragment.getInstance()
                    fragments.put(PAGE_PROJECT, fragment)
                } else {
                    fragment = fragments.get(PAGE_PROJECT)
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

        const val PAGE_FIND = 1

        const val PAGE_INDICATOR = 2

        const val PAGE_OTHERS = 3

        const val PAGE_PROJECT = 4

    }

}
