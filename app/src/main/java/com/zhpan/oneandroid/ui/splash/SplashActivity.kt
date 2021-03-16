package com.zhpan.oneandroid.ui.splash

import android.animation.Animator
import android.content.Intent
import android.os.Bundle
import com.zhpan.library.base.BaseActivity
import com.zhpan.library.base.BaseViewModel
import com.zhpan.oneandroid.R
import com.zhpan.oneandroid.databinding.ActivitySplashBinding
import com.zhpan.oneandroid.ui.main.MainActivity
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : BaseActivity<BaseViewModel, ActivitySplashBinding>() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme_TranslucentStatus)
        setTransparentStatusBar()
        setStatusBarDarkMode(false)
        lottie_view.addAnimatorListener(object : Animator.AnimatorListener {
            override fun onAnimationRepeat(animation: Animator?) {
            }

            override fun onAnimationEnd(animation: Animator?) {
                startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                finish()
            }

            override fun onAnimationCancel(animation: Animator?) {
            }

            override fun onAnimationStart(animation: Animator?) {
            }

        })
    }

    override fun getLayoutId(): Int = R.layout.activity_splash
}
