package com.zhpan.library.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.blankj.utilcode.util.BarUtils
import com.blankj.utilcode.util.ToastUtils
import com.zhpan.library.server.common.DataState
import java.lang.reflect.ParameterizedType

/**
 * @Description:
 * @Author: zhangpan
 * @Date: 2022/7/6 15:37
 * @Email: pan.zhang@upuphone.com
 */
abstract class NewBaseActivity<VM : NewBaseViewModel<*>, DB : ViewDataBinding> : AppCompatActivity() {

  protected val mViewModel by lazy {
    createViewModel()
  }

  protected lateinit var mBinding: DB

  /**
   * layout resource id.
   */
  abstract var layoutId: Int

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    mBinding = DataBindingUtil.setContentView(this, layoutId)
    mBinding.lifecycleOwner = this
    mViewModel.loadingDataState.observe(this, Observer {
      when (it) {
        DataState.STATE_LOADING ->
          showLoading()
        else ->
          hideLoading()
      }
    })
    onActivityCreated(savedInstanceState)
  }

  /**
   * 显示Loading
   */
  open fun showLoading() {
    ToastUtils.showShort("showLoading")
  }

  /**
   * 隐藏Loading
   */
  open fun hideLoading() {
    ToastUtils.showShort("hideLoading")
  }

  override fun onDestroy() {
    super.onDestroy()
    mBinding.unbind()
  }

  /**
   * Activity content view created.
   * @param savedInstanceState savedInstanceState
   */
  abstract fun onActivityCreated(savedInstanceState: Bundle?)

  /**
   * Create ViewModel
   * @return ViewModel
   */
  @Suppress("UNCHECKED_CAST")
  open fun createViewModel(): VM {
    val findViewModelClass = findViewModelClass<VM>(NewBaseViewModel::class.java)
      ?: return ViewModelProvider(this)[BaseViewModel::class.java] as VM
    if (BaseAndroidViewModel::class.java.isAssignableFrom(findViewModelClass)) {
      return ViewModelProvider(this, AppViewModelFactory(this))[findViewModelClass]
    }
    return ViewModelProvider(this)[findViewModelClass]
  }

  fun setTransparentStatusBar() {
    BarUtils.transparentStatusBar(this)
  }

  fun setStatusBarDarkMode(boolean: Boolean) {
    BarUtils.setStatusBarLightMode(this, boolean)
  }
}

/**
 * 获取子类的Class类型
 */
@Suppress("UNCHECKED_CAST")
fun <T> Any.findViewModelClass(cls: Class<*>) = run {
  var num = 0
  (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments.forEachIndexed { index, type ->
    if (cls.isAssignableFrom(type as Class<*>)) {
      num = index
      return@forEachIndexed
    }
  }
  (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[num] as Class<T>?
}

