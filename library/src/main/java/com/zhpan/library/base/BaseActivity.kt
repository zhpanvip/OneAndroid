package com.zhpan.library.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.blankj.utilcode.util.BarUtils
import com.zhpan.library.server.common.DataState
import com.zhpan.library.utils.LoadingUtils
import java.lang.reflect.Modifier
import java.lang.reflect.ParameterizedType

/**
 * @Description:
 * @Author: zhangpan
 * @Date: 2022/7/6 15:37
 * @Email: pan.zhang@upuphone.com
 */
abstract class BaseActivity<VM : BaseViewModel<*>, DB : ViewDataBinding> : AppCompatActivity() {

  protected val mViewModel by lazy {
    createViewModel()
  }

  protected lateinit var mBinding: DB

  private val loadingUtils: LoadingUtils by lazy {
    LoadingUtils()
  }

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
          dismissLoading()
      }
    })
    onActivityCreated(savedInstanceState)
  }

  /**
   * 显示Loading
   */
  open fun showLoading() {
    loadingUtils.showProgress(this)
  }

  /**
   * 隐藏Loading
   */
  open fun dismissLoading() {
    loadingUtils.dismissProgress()
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
    val actualGenericsClass = findActualGenericsClass<VM>(BaseViewModel::class.java)
      ?: throw NullPointerException("Can not find a BaseViewModel Generics in ${javaClass.simpleName}")
    if (Modifier.isAbstract(actualGenericsClass.modifiers)) {
      throw IllegalStateException("$actualGenericsClass is an abstract class,abstract ViewModel class can not create a instance!")
    }
    if (BaseAndroidViewModel::class.java.isAssignableFrom(actualGenericsClass)) {
      return ViewModelProvider(this, AppViewModelFactory(application))[actualGenericsClass]
    }
    return ViewModelProvider(this)[actualGenericsClass]
  }

  fun setTransparentStatusBar() {
    BarUtils.transparentStatusBar(this)
  }

  fun setStatusBarDarkMode(boolean: Boolean) {
    BarUtils.setStatusBarLightMode(this, boolean)
  }
}

/**
 * 查找 Any 类上泛型为[cls]类型的class，如果不存在则返回null
 * @param cls 要查找的泛型的类型
 */
@Suppress("UNCHECKED_CAST")
internal fun <T> Any.findActualGenericsClass(cls: Class<*>): Class<T>? {
  val genericSuperclass = javaClass.genericSuperclass
  if (genericSuperclass !is ParameterizedType) {
    return null
  }
  // 获取类的所有泛型参数数组
  val actualTypeArguments = genericSuperclass.actualTypeArguments
  // 遍历泛型数组
  actualTypeArguments.forEach {
    if (it is Class<*> && cls.isAssignableFrom(it)) {
      return it as Class<T>
    } else if (it is ParameterizedType) {
      val rawType = it.rawType
      if (rawType is Class<*> && cls.isAssignableFrom(rawType)) {
        return rawType as Class<T>
      }
    }
  }
  return null
}

