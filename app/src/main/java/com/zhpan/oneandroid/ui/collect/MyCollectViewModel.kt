package com.zhpan.oneandroid.ui.collect

import com.zhpan.library.base.BaseRepository
import com.zhpan.library.base.BaseViewModel

class MyCollectViewModel:BaseViewModel<BaseRepository>() {
  override fun createRepository(): BaseRepository {
    return BaseRepository()
  }
}