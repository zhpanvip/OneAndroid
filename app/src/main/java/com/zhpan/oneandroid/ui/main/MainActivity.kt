package com.zhpan.oneandroid.ui.main

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Debug
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.zhpan.library.base.BaseActivity
import com.zhpan.library.base.BaseViewModel
import com.zhpan.oneandroid.R
import kotlinx.android.synthetic.main.activity_main.*
import com.zhpan.oneandroid.databinding.ActivityMainBinding
import com.zhpan.oneandroid.ui.collect.MyCollectActivity
import com.zhpan.oneandroid.ui.jifen.IntegralActivity
import com.zhpan.oneandroid.ui.login.LoginActivity
import com.zhpan.oneandroid.ui.setting.SettingActivity
import com.zhpan.oneandroid.ui.share.MyShareActivity
import com.zhpan.oneandroid.ui.todo.ToDoActivity
import com.zhpan.oneandroid.utils.UserInfoHelper

class MainActivity : BaseActivity<BaseViewModel, ActivityMainBinding>() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        initView()
        setListener()
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
//        Debug.stopMethodTracing()
    }

    private fun initView() {
        setTransparentStatusBar()
        with(viewPager2) {
            adapter = MainFragmentStateAdapter(this@MainActivity)
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    bottomNavigationView.menu.getItem(position).isChecked = true
                }
            })
            offscreenPageLimit = 4
            isUserInputEnabled = false
        }
        toolbar.apply {
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
                val clazz: Class<out Activity> = when (item.itemId) {
                    R.id.nav_collect ->
                        MyCollectActivity::class.java
                    R.id.nav_integral -> IntegralActivity::class.java
                    R.id.nav_setting -> SettingActivity::class.java
                    R.id.nav_to_do -> ToDoActivity::class.java
                    R.id.nav_share -> MyShareActivity::class.java
                    else -> MyCollectActivity::class.java
                }
                startActivity(Intent(this@MainActivity, clazz))
                true
            }
        }
        mBinding.title = getString(R.string.tab_home)
        nav_view.getHeaderView(0).setOnClickListener {
            if (!UserInfoHelper.isLogin()) {
                startActivity(Intent(this, LoginActivity::class.java))
            }
        }
        val userName = nav_view.getHeaderView(0).findViewById<TextView>(R.id.tv_user_id)
        val userAvatar = nav_view.getHeaderView(0).findViewById<ImageView>(R.id.avatar)
        userName.text = UserInfoHelper.getUserName()
        Glide.with(userAvatar).load(UserInfoHelper.getUserAvatarUrl())
                .placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(userAvatar);
    }

    private fun setListener() {
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            val position = when (item.itemId) {
                R.id.item_tab1 -> {
                    mBinding.title = getString(R.string.tab_home)
                    0
                }
                R.id.item_tab2 -> {
                    mBinding.title = getString(R.string.tab_square)
                    1
                }
                R.id.item_tab3 -> {
                    mBinding.title = getString(R.string.tab_official_account)
                    2
                }
                R.id.item_tab4 -> {
                    mBinding.title = getString(R.string.tab_knowledge_map)
                    3
                }
                R.id.item_tab5 -> {
                    mBinding.title = getString(R.string.tab_project)
                    4
                }
                else -> 0
            }
            viewPager2.setCurrentItem(position, false)
            false
        }
    }

    override fun getLayoutId(): Int = R.layout.activity_main
}
