package com.zhpan.oneandroid.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.blankj.utilcode.util.ConvertUtils
import com.zhpan.bannerview.BannerViewPager
import com.zhpan.bannerview.adapter.OnPageChangeListenerAdapter
import com.zhpan.bannerview.constants.IndicatorGravity
import com.zhpan.indicator.enums.IndicatorSlideMode
import com.zhpan.oneandroid.R
import com.zhpan.oneandroid.viewholder.WelcomeViewHolder
import kotlinx.android.synthetic.main.activity_welcome.*

class WelcomeActivity : AppCompatActivity() {

    private lateinit var mBannerViewPager: BannerViewPager<Int, WelcomeViewHolder>

    private var res: IntArray = intArrayOf(
        R.drawable.img_welcome_1,
        R.drawable.img_welcome_2,
        R.drawable.img_welcome_4,
        R.drawable.img_welcome_3
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        initViewPager()
    }

    private fun initViewPager() {
        mBannerViewPager = findViewById(R.id.bannerView)
        mBannerViewPager.setCanLoop(false)
            .setIndicatorSlideMode(IndicatorSlideMode.SMOOTH)
            .setIndicatorMargin(0, 0, 0, ConvertUtils.dp2px(40f))
            .setIndicatorGravity(IndicatorGravity.CENTER)
            .setHolderCreator { WelcomeViewHolder() }
            .setOnPageChangeListener(
                object : OnPageChangeListenerAdapter() {
                    override fun onPageSelected(position: Int) {
                        pageSelect(position)
                    }
                }
            )
            .create(res.toList())
    }

    private fun pageSelect(position: Int) {
        if (position == res.size - 1) {
            btn_start.visibility = View.VISIBLE
            btn_start.setOnClickListener { onButtonClick() }
        } else {
            btn_start.visibility = View.GONE
        }
    }


    private fun onButtonClick() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}
