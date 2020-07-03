package com.zhpan.oneandroid.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.zhpan.oneandroid.R
import com.zhpan.oneandroid.adapter.MainFragmentPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        with(viewPager2) {
            adapter = MainFragmentPagerAdapter(this@MainActivity)
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    bottomNavigationView.menu.getItem(position).isChecked = true
                }
            })
        }

        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.item_tab1 -> {
                    viewPager2.currentItem = 0
                }
                R.id.item_tab2 -> {
                    viewPager2.currentItem = 1
                }
                R.id.item_tab3 -> {
                    viewPager2.currentItem = 2
                }
                R.id.item_tab4 -> {
                    viewPager2.currentItem = 3
                }
                R.id.item_tab5 -> {
                    viewPager2.currentItem = 4
                }
            }
            false
        }
    }
}
