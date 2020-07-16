package com.zhpan.oneandroid.ui

import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.widget.ViewPager2
import com.zhpan.library.base.BaseActivity
import com.zhpan.library.base.BaseViewModel
import com.zhpan.oneandroid.R
import com.zhpan.oneandroid.adapter.MainFragmentStateAdapter
import kotlinx.android.synthetic.main.activity_main.*
import com.zhpan.oneandroid.databinding.ActivityMainBinding

class MainActivity : BaseActivity<BaseViewModel,ActivityMainBinding>() {

   override fun onActivityCreated(savedInstanceState: Bundle?) {
        initView()
        setListener()
    }

    private fun initView() {
        with(viewPager2) {
            adapter = MainFragmentStateAdapter(this@MainActivity)
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    bottomNavigationView.menu.getItem(position).isChecked = true
                }
            })
            offscreenPageLimit=4
        }
        toolbar.apply {
            title = getString(R.string.app_name)
            setSupportActionBar(this)
        }
        drawerLayout.apply {
            val drawerToggle = ActionBarDrawerToggle(
                this@MainActivity,
                this,
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
            )
            addDrawerListener(drawerToggle)
            drawerToggle.syncState()
        }
        nav_view.apply {
            setNavigationItemSelectedListener { item ->
                when (item.itemId) {

                }
                true
            }
        }
    }

    private fun setListener() {
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

   override fun getLayoutId(): Int = R.layout.activity_main
}
