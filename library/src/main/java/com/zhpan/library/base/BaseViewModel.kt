package com.zhpan.library.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zhpan.library.server.common.DataState
import java.lang.NullPointerException

/**
 * <pre>
 *   Created by zhpan on 2020/7/5.
 *   Description:
 * </pre>
 */
abstract class BaseViewModel<R : BaseRepository> : ViewModel() {

  val repository: R by lazy {
    createRepository()
  }

  val loadingDataState: MutableLiveData<DataState> by lazy {
    repository.loadingStateLiveData
  }

  open fun createRepository(): R {
    val baseRepository = findActualGenericsClass<R>(BaseRepository::class.java)
      ?: throw NullPointerException("Can not find a BaseRepository Generics in ${javaClass.simpleName}")
    return baseRepository.newInstance()
  }
}