package com.zhpan.oneandroid.ui.wechat

import androidx.databinding.ViewDataBinding
import com.zhpan.library.base.BaseFragment
import com.zhpan.library.base.BaseViewModel
import com.zhpan.oneandroid.R


/**
 * <pre>
 *   Created by zhpan on 2020/7/4.
 *   Description:
 * </pre>
 */
class WechatFragment : BaseFragment<BaseViewModel,ViewDataBinding>() {

    companion object{
        fun getInstance(): WechatFragment {
            return WechatFragment()
        }
    }

    override fun fetchData() {
    }

    override fun onViewInflate() {
    }

    override fun getLayoutId(): Int {
        return R.layout.layout_article_list
    }


}