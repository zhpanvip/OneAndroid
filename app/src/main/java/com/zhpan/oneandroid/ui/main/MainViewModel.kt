package com.zhpan.oneandroid.ui.main

import com.zhpan.library.base.BaseRepository
import com.zhpan.library.base.BaseViewModel

class MainViewModel : BaseViewModel<BaseRepository>() {
  override fun createRepository(): BaseRepository {
    return BaseRepository()
  }
}