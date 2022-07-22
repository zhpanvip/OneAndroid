package com.zhpan.oneandroid.ui.splash

import com.zhpan.library.base.BaseRepository
import com.zhpan.library.base.BaseViewModel

class SplashViewModel : BaseViewModel<BaseRepository>() {
  override fun createRepository(): BaseRepository {
    return BaseRepository()
  }
}