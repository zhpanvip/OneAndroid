package com.zhpan.oneandroid.ui.collect

import com.zhpan.library.base.BaseRepository
import com.zhpan.library.base.NewBaseViewModel

class MyCollectViewModel:NewBaseViewModel<BaseRepository>() {
  override fun createRepository(): BaseRepository {
    return BaseRepository()
  }
}