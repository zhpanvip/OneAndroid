package com.zhpan.library.utils;

import android.content.Context;

import com.zhpan.library.R;
import com.zhpan.library.dialog.LoadingDialog;


/**
 * Created by zhpan on 2017/5/26.
 * Description:
 */

public class LoadingUtils {
    private LoadingDialog mProgressDialog;
    /**
     * 显示ProgressDialog
     */
    public void showProgress(Context context, String msg) {
       /* if (context == null || context.isFinishing()) {
            return;
        }*/
        if(mProgressDialog==null){
            mProgressDialog= new LoadingDialog.Builder(context)
                    .setTheme(R.style.ProgressDialogStyle)
                    .setMessage(msg)
                    .build();
        }
        if(!mProgressDialog.isShowing()) {
            mProgressDialog.show();
        }
    }

    /**
     * 显示ProgressDialog
     */
    public void showProgress(Context context) {
        /*if (activity == null || activity.isFinishing()) {
            return;
        }*/
        if(mProgressDialog==null){
            mProgressDialog= new LoadingDialog.Builder(context)
                    .setTheme(R.style.ProgressDialogStyle)
                    .build();
        }
        if(!mProgressDialog.isShowing()) {
            mProgressDialog.show();
        }
    }

    /**
     * 取消ProgressDialog
     */
    public void dismissProgress() {
        if (mProgressDialog != null&&mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }
}
