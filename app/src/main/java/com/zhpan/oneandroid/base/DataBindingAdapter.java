package com.zhpan.oneandroid.base;

import android.content.res.Resources;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.zhpan.bannerview.BannerViewPager;
import com.zhpan.bannerview.constants.PageStyle;
import com.zhpan.indicator.base.BaseIndicatorView;
import com.zhpan.indicator.enums.IndicatorSlideMode;
import com.zhpan.indicator.enums.IndicatorStyle;
import com.zhpan.library.view.CornerImageView;
import com.zhpan.oneandroid.R;
import com.zhpan.oneandroid.adapter.BannerAdapter;
import com.zhpan.oneandroid.adapter.BannerViewHolder;
import com.zhpan.oneandroid.model.bean.BannerBean;

/**
 * <pre>
 *   Created by zhpan on 2020/7/18.
 *   Description:
 * </pre>
 */
public class DataBindingAdapter {
    @BindingAdapter(value = {"binding:adapter", "binding:indicator", "binding:pageClick"}, requireAll = false)
    public static void
    bindBanner(BannerViewPager<BannerBean, BannerViewHolder> banner,
               BannerAdapter adapter, BaseIndicatorView indicatorView,
               BannerViewPager.OnPageClickListener pageClickListener) {
        Resources resources = banner.getContext().getResources();
        banner.setIndicatorSlideMode(IndicatorSlideMode.SCALE)
                .setIndicatorStyle(IndicatorStyle.ROUND_RECT)
                .setAdapter(adapter)
                .setAutoPlay(true)
                .setScrollDuration(700)
                .setIndicatorView(indicatorView)
                .setOnPageClickListener(pageClickListener)
                .setIndicatorSliderColor(resources.getColor(R.color.color_slider_normal),
                        resources.getColor(R.color.colorPrimary))
                .setPageStyle(PageStyle.MULTI_PAGE_SCALE)
                .setIndicatorSliderWidth(
                        resources.getDimensionPixelOffset(R.dimen.dp_4),
                        resources.getDimensionPixelOffset(R.dimen.dp_10))
                .setIndicatorSliderGap(resources.getDimensionPixelOffset(R.dimen.dp_4)).create();
    }

    @BindingAdapter(value = {"binding:url", "binding:placeholder"}, requireAll = false)
    public static void bindUrl(CornerImageView imageView, String url, int placeholder) {
        Glide.with(imageView).load(url).error(placeholder).placeholder(placeholder).into(imageView);
    }

    @BindingAdapter(value = {"binding:itemClick"})
    public static void bindItemClick(RecyclerView recyclerView, OnItemClickListener onItemClickListener) {
        RecyclerView.Adapter adapter = recyclerView.getAdapter();
        if (adapter instanceof BaseQuickAdapter) {
            ((BaseQuickAdapter) adapter).setOnItemClickListener(onItemClickListener);
        }
    }

}
