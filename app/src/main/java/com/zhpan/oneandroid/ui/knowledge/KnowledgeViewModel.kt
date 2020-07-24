package com.zhpan.oneandroid.ui.knowledge

import androidx.lifecycle.MutableLiveData
import com.zhpan.library.base.BaseViewModel
import com.zhpan.library.base.IFragmentHost
import com.zhpan.oneandroid.model.bean.KnowledgeBean

/**
 *
 * @author zhangpan
 * @date 2020/7/24
 */
class KnowledgeViewModel(private var repository: KnowledgeRepository) : BaseViewModel() {
    fun getSystemClassify(
        iFragment: IFragmentHost,
        showLoading: Boolean
    ): MutableLiveData<List<KnowledgeBean>> {
        return repository.getSystemClassify(iFragment, showLoading)
    }
}