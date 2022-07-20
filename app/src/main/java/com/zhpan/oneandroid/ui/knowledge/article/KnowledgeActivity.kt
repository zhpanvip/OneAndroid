package com.zhpan.oneandroid.ui.knowledge.article

import android.os.Bundle
import com.google.android.material.tabs.TabLayoutMediator
import com.zhpan.library.base.BaseActivity
import com.zhpan.oneandroid.R
import com.zhpan.oneandroid.adapter.KnowledgeFragmentAdapter
import com.zhpan.oneandroid.base.Constants
import com.zhpan.oneandroid.databinding.ActivitySystemBinding
import com.zhpan.oneandroid.model.bean.KnowledgeBean
import com.zhpan.oneandroid.ui.knowledge.KnowledgeFragment.Companion.SYSTEM_MAP_ITEMS
import kotlinx.android.synthetic.main.activity_system.*

class KnowledgeActivity(override var layoutId: Int = R.layout.activity_system) :
  BaseActivity<KnowledgeViewModel, ActivitySystemBinding>() {

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    val bundleExtra = intent.getBundleExtra(SYSTEM_MAP_ITEMS)
    val knowledgeList = bundleExtra?.getSerializable(SYSTEM_MAP_ITEMS) as List<KnowledgeBean>
    viewPager2.adapter = KnowledgeFragmentAdapter(this, knowledgeList)
    mBinding.title = bundleExtra.getString(Constants.KEY_KNOWLEDGE_TITLE)
    TabLayoutMediator(
      tabLayout,
      viewPager2
    ) { tab, position ->
      tab.text = knowledgeList[position].name
    }.attach()
  }
}