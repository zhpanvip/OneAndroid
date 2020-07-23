package com.zhpan.library.view

import android.content.Context
import android.os.Build
import android.util.AttributeSet

import androidx.appcompat.widget.AppCompatImageView

import com.zhpan.bannerview.provider.ViewStyleSetter
import com.zhpan.library.R

class CornerImageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatImageView(context, attrs, defStyleAttr) {

    init {
        if (attrs != null) {
            val typeArray =
                context.obtainStyledAttributes(attrs, R.styleable.CornerImageView)
            val radius =
                typeArray.getDimensionPixelOffset(R.styleable.CornerImageView_civ_radius, 0)
            setRoundCorner(radius)
            typeArray.recycle()
        }
    }

    fun setRoundCorner(radius: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val viewStyleSetter = ViewStyleSetter(this)
            viewStyleSetter.setRoundRect(radius.toFloat())
        }
    }
}
