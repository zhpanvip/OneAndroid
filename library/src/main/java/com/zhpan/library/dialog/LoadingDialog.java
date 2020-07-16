package com.zhpan.library.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.zhpan.library.R;

public class LoadingDialog extends Dialog {
    private View mDialogView;
    private boolean cancelTouchOutside;
    private LottieAnimationView lottieAnimationView;

    public LoadingDialog(Builder builder) {
        super(builder.context);
        mDialogView = builder.mDialogView;
        cancelTouchOutside = builder.cancelTouchOutside;
    }

    private LoadingDialog(Builder builder, int themeResId) {
        super(builder.context, themeResId);
        mDialogView = builder.mDialogView;
        cancelTouchOutside = builder.cancelTouchOutside;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(mDialogView);
        setCanceledOnTouchOutside(cancelTouchOutside);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        if (mDialogView == null) {
            return;
        }
        lottieAnimationView = mDialogView.findViewById(R.id.loadingImageView);
        lottieAnimationView.playAnimation();
    }

    @Override
    protected void onStop() {
        super.onStop();
        lottieAnimationView.pauseAnimation();
    }

    public static final class Builder {
        Context context;
        private int resStyle = -1;
        private View mDialogView;
        private boolean cancelTouchOutside;

        public Builder(Context context) {
            this.context = context;
            mDialogView = LayoutInflater.from(context).inflate(R.layout.layout_loading_dialog, null);
        }

        /**
         * 设置主题
         *
         * @param resStyle style id
         * @return CustomProgressDialog.Builder
         */
        public Builder setTheme(int resStyle) {
            this.resStyle = resStyle;
            return this;
        }

        public Builder setMessage(String message) {
            TextView tvMessage = (TextView) mDialogView.findViewById(R.id.tv_loading);
            if (tvMessage != null) {
                tvMessage.setText(message);
            }
            return this;
        }

        /**
         * 设置点击dialog外部是否取消dialog
         *
         * @param val 点击外部是否取消dialog
         * @return
         */
        public Builder cancelTouchOutside(boolean val) {
            cancelTouchOutside = val;
            return this;
        }

        public LoadingDialog build() {
            if (resStyle != -1) {
                return new LoadingDialog(this, resStyle);
            } else {
                return new LoadingDialog(this);
            }
        }
    }
}
