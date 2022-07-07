package com.zhpan.library.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.scwang.smart.refresh.footer.BallPulseFooter
import com.scwang.smart.refresh.header.MaterialHeader
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import com.scwang.smart.refresh.layout.api.RefreshFooter
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.constant.SpinnerStyle
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener
import com.scwang.smart.refresh.layout.listener.OnRefreshListener
import com.zhpan.library.R

/**
 * @Description:
 * @Author: zhangpan
 * @Date: 2022/7/6 15:37
 * @Email: pan.zhang@upuphone.com
 */
abstract class NewBaseFragment<VM : NewBaseViewModel<*>, T : ViewDataBinding> : Fragment(),
  OnRefreshListener,
  OnLoadMoreListener {
  protected var page: Int = 0
  protected var mRefreshLayout: SmartRefreshLayout? = null
  protected val mViewModel: VM by lazy {
    createViewModel()
  }

  private var isDataLoaded: Boolean = false
   lateinit var mBinding: T

  /**
   * layout id
   */
  abstract val layoutId: Int

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    mBinding = DataBindingUtil.inflate(inflater, layoutId, container, false)
    mBinding.lifecycleOwner = viewLifecycleOwner
    return mBinding.root
  }


  override fun onResume() {
    super.onResume()
    // 处理懒加载
    if (!isDataLoaded) {
      isDataLoaded = true
      onLazyLoad()
    }
  }

  abstract fun onLazyLoad()

  /**
   * Create ViewModel
   * @return ViewModel
   */
  @Suppress("UNCHECKED_CAST")
  open fun createViewModel(): VM {
    val findViewModelClass = findViewModelClass<VM>(BaseViewModel::class.java)
      ?: return ViewModelProvider(this)[BaseViewModel::class.java] as VM
    if (BaseAndroidViewModel::class.java.isAssignableFrom(findViewModelClass)) {
      return ViewModelProvider(this, AppViewModelFactory(requireContext()))[findViewModelClass]
    }
    return ViewModelProvider(this)[findViewModelClass]
  }

  protected open fun setRefreshLayout(@IdRes resId: Int) {
    mRefreshLayout = mBinding.root.findViewById(resId)
    if (needRefreshHeader()) {
      mRefreshLayout?.setRefreshHeader(getRefreshHeader())
      mRefreshLayout?.setRefreshFooter(getRefreshFooter())
    }
    mRefreshLayout?.setEnableLoadMore(true)
    mRefreshLayout?.setOnRefreshListener(this)
    mRefreshLayout?.setOnLoadMoreListener(this)
  }

  private fun getRefreshFooter(): RefreshFooter {
    val ballFooter =
      BallPulseFooter(requireContext()).setSpinnerStyle(SpinnerStyle.FixedBehind)
    ballFooter.setAnimatingColor(requireContext().resources.getColor(R.color.colorPrimary))
    return ballFooter
  }

  protected open fun needRefreshHeader(): Boolean {
    return true
  }

  //  获取刷新头
  protected open fun getRefreshHeader(): MaterialHeader {
    val materialHeader = MaterialHeader(context)
    materialHeader.setColorSchemeResources(R.color.colorPrimaryDark)
    return materialHeader
  }

  override fun onRefresh(refreshLayout: RefreshLayout) {
    page = 0
  }

  override fun onLoadMore(refreshLayout: RefreshLayout) {

  }
}