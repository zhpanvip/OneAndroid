package com.zhpan.oneandroid.ui.main

import com.zhpan.library.base.BaseRepository
import com.zhpan.library.base.NewBaseViewModel

class MainViewModel : NewBaseViewModel<BaseRepository>() {
  override fun createRepository(): BaseRepository {
    return BaseRepository()
  }
}