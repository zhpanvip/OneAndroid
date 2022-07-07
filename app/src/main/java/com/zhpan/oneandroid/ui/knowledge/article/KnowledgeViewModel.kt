package com.zhpan.oneandroid.ui.knowledge.article

import com.zhpan.library.base.BaseRepository
import com.zhpan.library.base.NewBaseViewModel

class KnowledgeViewModel:NewBaseViewModel<BaseRepository>() {
  override fun createRepository(): BaseRepository {
    return BaseRepository()
  }
}