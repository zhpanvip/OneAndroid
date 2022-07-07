package com.zhpan.library.base

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalStateException
import java.lang.reflect.InvocationTargetException

/**
 * @Description: 创建 [BaseAndroidViewModel]
 * @Author: zhangpan
 * @Date: 2022/7/7 14:37
 * @Email: pan.zhang@upuphone.com
 */
class AppViewModelFactory(private val context: Context) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (BaseAndroidViewModel::class.java.isAssignableFrom(modelClass)) {
            try {
                modelClass.getConstructor(Context::class.java).newInstance(context)
            } catch (e: NoSuchMethodException) {
                throw IllegalStateException("Cannot create an instance of $modelClass", e)
            } catch (e: IllegalAccessException) {
                throw IllegalStateException("Cannot create an instance of $modelClass", e)
            } catch (e: InstantiationException) {
                throw IllegalStateException("Cannot create an instance of $modelClass", e)
            } catch (e: InvocationTargetException) {
                throw IllegalStateException("Cannot create an instance of $modelClass", e)
            }
        } else super.create(modelClass)
    }
}