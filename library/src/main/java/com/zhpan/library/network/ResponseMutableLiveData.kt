package com.zhpan.library.network

import com.zhpan.library.server.common.BasicResponse

/**
 * @Description:
 * @Author: zhangpan
 * @Date: 2022/1/28 14:26
 * @Email: zhpanvip@outlook.com
 */
class ResponseMutableLiveData<T> : ResponseLiveData<T> {
    
    /**
     * Creates a MutableLiveData initialized with the given `value`.
     *
     * @param value initial value
     */
    constructor(value: BasicResponse<T>?) : super(value)
    
    /**
     * Creates a MutableLiveData with no value assigned to it.
     */
    constructor() : super()
    
    public override fun postValue(value: BasicResponse<T>?) {
        super.postValue(value)
    }
    
    public override fun setValue(value: BasicResponse<T>?) {
        super.setValue(value)
    }
}