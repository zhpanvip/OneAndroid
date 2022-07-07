package com.zhpan.oneandroid.ui.splash

import com.zhpan.library.base.BaseRepository
import com.zhpan.library.base.NewBaseViewModel

class SplashViewModel : NewBaseViewModel<BaseRepository>() {
  override fun createRepository(): BaseRepository {
    return BaseRepository()
  }
}