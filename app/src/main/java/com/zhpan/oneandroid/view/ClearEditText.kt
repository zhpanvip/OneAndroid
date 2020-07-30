package com.zhpan.oneandroid.view

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.View.OnFocusChangeListener
import android.view.View.OnTouchListener
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import com.zhpan.oneandroid.R
import java.util.*

/**
 * author : Android 轮子哥
 * github : https://github.com/getActivity/AndroidProject
 * time   : 2018/10/18
 * desc   : 带清除按钮的 EditText
 */
class ClearEditText @SuppressLint("ClickableViewAccessibility") constructor(
    context: Context?,
    attrs: AttributeSet?,
    defStyleAttr: Int
) : RegexEditText(context!!, attrs, defStyleAttr), OnTouchListener, OnFocusChangeListener,
    TextWatcher {
    private val mClearDrawable: Drawable
    private var mOnTouchListener: OnTouchListener? = null
    private var mOnFocusChangeListener: OnFocusChangeListener? = null

    @JvmOverloads
    constructor(context: Context?, attrs: AttributeSet? = null) : this(
        context,
        attrs,
        android.R.attr.editTextStyle
    ) {
    }

    private fun setDrawableVisible(visible: Boolean) {
        if (mClearDrawable.isVisible == visible) {
            return
        }
        mClearDrawable.setVisible(visible, false)
        val drawables = compoundDrawables
        setCompoundDrawables(
            drawables[0],
            drawables[1],
            if (visible) mClearDrawable else null,
            drawables[3]
        )
    }

    override fun setOnFocusChangeListener(onFocusChangeListener: OnFocusChangeListener) {
        mOnFocusChangeListener = onFocusChangeListener
    }

    override fun setOnTouchListener(onTouchListener: OnTouchListener) {
        mOnTouchListener = onTouchListener
    }

    /**
     * [OnFocusChangeListener]
     */
    override fun onFocusChange(
        view: View,
        hasFocus: Boolean
    ) {
        if (hasFocus && text != null) {
            setDrawableVisible(text!!.length > 0)
        } else {
            setDrawableVisible(false)
        }
        if (mOnFocusChangeListener != null) {
            mOnFocusChangeListener!!.onFocusChange(view, hasFocus)
        }
    }

    /**
     * [OnTouchListener]
     */
    override fun onTouch(view: View, motionEvent: MotionEvent): Boolean {
        val x = motionEvent.x.toInt()
        if (mClearDrawable.isVisible && x > width - paddingRight - mClearDrawable.intrinsicWidth) {
            if (motionEvent.action == MotionEvent.ACTION_UP) {
                setText("")
            }
            return true
        }
        return mOnTouchListener != null && mOnTouchListener!!.onTouch(view, motionEvent)
    }

    /**
     * [TextWatcher]
     */
    override fun onTextChanged(
        s: CharSequence,
        start: Int,
        before: Int,
        count: Int
    ) {
        if (isFocused) {
            setDrawableVisible(s.length > 0)
        }
    }

    override fun beforeTextChanged(
        s: CharSequence,
        start: Int,
        count: Int,
        after: Int
    ) {
    }

    override fun afterTextChanged(s: Editable) {}

    init {
        mClearDrawable = DrawableCompat.wrap(
            ContextCompat.getDrawable(
                context!!,
                R.drawable.input_delete_ic
            )!!
        )
        mClearDrawable.setBounds(
            0,
            0,
            mClearDrawable.intrinsicWidth,
            mClearDrawable.intrinsicHeight
        )
        setDrawableVisible(false)
        super.setOnTouchListener(this)
        super.setOnFocusChangeListener(this)
        super.addTextChangedListener(this)
    }
}