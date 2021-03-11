package com.zhpan.oneandroid.behavior;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;

/**
 * Created by chenxz on 2017/11/25.
 */
public class BottomNavigationBehavior extends CoordinatorLayout.Behavior<View> {
    private ObjectAnimator translateAnimator;
    private static final long ANIMATE_SCROLL_DURATION = 250;
    private static final Interpolator INTERPOLATOR = new FastOutSlowInInterpolator();

    public BottomNavigationBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    // 垂直滑动
    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View directTargetChild, @NonNull View target, int nestedScrollAxes, int type) {
        return nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL;
    }

    @Override
    public void onNestedPreScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View target, int dx, int dy, @NonNull int[] consumed, int type) {
        if (translateAnimator == null) {
            translateAnimator = ObjectAnimator.ofFloat(child, "translationY", 0, child.getHeight() + getMarginBottom(child));
            translateAnimator.setDuration(ANIMATE_SCROLL_DURATION).setInterpolator(INTERPOLATOR);
        }
        if (dy > 0) {
            // 开启隐藏动画
            if (!translateAnimator.isRunning() && child.getTranslationY() <= 0) {
                translateAnimator.start();
            }
        } else if (dy < 0) {
            // 开启显示动画
            if (!translateAnimator.isRunning() && child.getTranslationY() >= child.getHeight()) {
                translateAnimator.reverse();
            }
        }
    }

    private int getMarginBottom(View v) {
        final ViewGroup.LayoutParams layoutParams = v.getLayoutParams();
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            return ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin;
        }
        return 0;
    }
}
