package com.zhpan.oneandroid.ui

import android.os.Bundle
import android.view.View
import com.zhpan.oneandroid.R


/**
 * <pre>
 *   Created by zhpan on 2020/7/4.
 *   Description:
 * </pre>
 */
class HomeFragment : BaseFragment() {

    companion object{
        fun getInstance(): HomeFragment {
            return HomeFragment();
        }
    }

    override val layout: Int
        get() = R.layout.fragment_home

    override fun initTitle() {

    }

    override fun initView(savedInstanceState: Bundle?, view: View) {
    }


}