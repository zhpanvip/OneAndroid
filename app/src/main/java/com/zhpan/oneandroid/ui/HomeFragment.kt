package com.zhpan.oneandroid.ui

import androidx.databinding.ViewDataBinding
import com.blankj.utilcode.util.ToastUtils
import com.zhpan.library.base.BaseVMFragment
import com.zhpan.library.base.BaseViewModel
import com.zhpan.library.server.common.ResponseObserver
import com.zhpan.oneandroid.R
import com.zhpan.oneandroid.api.RetrofitCreator
import com.zhpan.oneandroid.module.response.LoginResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * <pre>
 *   Created by zhpan on 2020/7/4.
 *   Description:
 * </pre>
 */
class HomeFragment : BaseVMFragment<BaseViewModel, ViewDataBinding>() {

    companion object {
        fun getInstance(): HomeFragment {
            return HomeFragment()
        }
    }


    override fun initFragment() {
        btn_login.setOnClickListener() {
            val map = HashMap<String, String>()
            map["password"] = "abc123"
            map["username"] = "1001001"
            RetrofitCreator.apiService?.login(map)
                ?.observeOn(AndroidSchedulers.mainThread())?.subscribeOn(Schedulers.io())
                ?.subscribe(object : ResponseObserver<LoginResponse>() {
                    override fun onSuccess(response: LoginResponse?) {
                        ToastUtils.showShort("登录成功")
                    }
                })
        }
    }

    override fun onViewInflate() {
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_home
    }

}