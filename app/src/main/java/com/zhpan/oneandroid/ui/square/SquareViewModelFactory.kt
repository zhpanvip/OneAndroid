package com.zhpan.oneandroid.ui.square

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zhpan.oneandroid.ui.home.HomeViewModel
import java.lang.IllegalArgumentException


/**
 * <pre>
 *   Created by zhpan on 2020/7/18.
 *   Description:
 * </pre>
 */
class SquareViewModelFactory(private var repository: SquareRepository) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SquareViewModel::class.java)) {
            return SquareViewModel(repository) as T
        }
        throw IllegalArgumentException("Error ViewModel Type")
    }

}