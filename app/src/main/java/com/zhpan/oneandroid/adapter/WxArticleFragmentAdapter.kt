package com.zhpan.oneandroid.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.zhpan.oneandroid.model.bean.OfficialAccountBean
import com.zhpan.oneandroid.ui.wechat.article.WxArticleFragment

/**
 *
 * @author zhangpan
 * @date 2020/7/23
 */
class WxArticleFragmentAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {

    var officialAccountList: List<OfficialAccountBean> = ArrayList()

    override fun getItemCount(): Int {
        return officialAccountList.size
    }

    override fun createFragment(position: Int): Fragment {
        return WxArticleFragment.newInstance(officialAccountList[position].id.toString())
    }
}