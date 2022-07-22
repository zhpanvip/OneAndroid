package com.zhpan.oneandroid.ui.knowledge.article

import com.zhpan.library.base.BaseRepository
import com.zhpan.library.base.BaseViewModel

class KnowledgeViewModel : BaseViewModel<BaseRepository>() {
  override fun createRepository(): BaseRepository {
    return BaseRepository()
  }
}