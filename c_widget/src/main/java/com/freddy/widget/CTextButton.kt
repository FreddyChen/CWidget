package com.freddy.widget

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.StateListDrawable
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import com.freddy.widget.utils.DensityUtil
import kotlin.properties.Delegates

class CTextButton : AppCompatTextView {

    private var normalBackgroundColor: Int by Delegates.notNull()
    private var pressedBackgroundColor: Int by Delegates.notNull()
    private var disabledBackgroundColor: Int by Delegates.notNull()
    private var normalTextColor: Int? = null
    private var pressedTextColor: Int by Delegates.notNull()
    private var disabledTextColor: Int? = null
    private var normalStrokeColor: Int by Delegates.notNull()
    private var pressedStrokeColor: Int by Delegates.notNull()
    private var disabledStrokeColor: Int by Delegates.notNull()
    private var strokeWidth: Int by Delegates.notNull()
    private var dashWidth: Float by Delegates.notNull()
    private var dashGap: Float by Delegates.notNull()
    private var cornerRadius: Float by Delegates.notNull()
    private var shape: Int by Delegates.notNull()

    companion object {
        private const val RECTANGLE = 0
        private const val OVAL = 1
        private const val LINE = 2
        private const val RING = 3
    }

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        val array = context.obtainStyledAttributes(attrs, R.styleable.CTextButton, defStyleAttr, 0)
        normalBackgroundColor = array.getColor(
            R.styleable.CTextButton_ctb_normalBackgroundColor,
            Color.parseColor("#0072ff")
        )
        pressedBackgroundColor = array.getColor(
            R.styleable.CTextButton_ctb_pressedBackgroundColor,
            Color.parseColor("#0a54bc")
        )
        disabledBackgroundColor = array.getColor(
            R.styleable.CTextButton_ctb_disabledBackgroundColor,
            Color.parseColor("#c9c9c9")
        )
        normalTextColor =
            array.getColor(R.styleable.CTextButton_ctb_normalTextColor, Color.parseColor("#ffffff"))
        pressedTextColor = array.getColor(
            R.styleable.CTextButton_ctb_pressedTextColor,
            Color.parseColor("#fefeff")
        )
        disabledTextColor = array.getColor(
            R.styleable.CTextButton_ctb_disabledTextColor,
            Color.parseColor("#a7a7a7")
        )
        normalStrokeColor = array.getColor(R.styleable.CTextButton_ctb_normalStrokeColor, -1)
        pressedStrokeColor = array.getColor(R.styleable.CTextButton_ctb_pressedStrokeColor, -1)
        disabledStrokeColor = array.getColor(R.styleable.CTextButton_ctb_disabledStrokeColor, -1)
        strokeWidth = array.getDimensionPixelSize(R.styleable.CTextButton_ctb_strokeWidth, 0)
        dashWidth = array.getDimensionPixelSize(R.styleable.CTextButton_ctb_dashWidth, 0).toFloat()
        dashGap = array.getDimensionPixelSize(R.styleable.CTextButton_ctb_dashGap, 0).toFloat()
        cornerRadius = array.getDimensionPixelSize(
            R.styleable.CTextButton_ctb_cornerRadius,
            DensityUtil.dip2px(context, 6.0f)
        ).toFloat()
        shape = array.getInt(R.styleable.CTextButton_ctb_shape, RECTANGLE)
        array.recycle()
        init()
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        if(normalTextColor == null || disabledTextColor == null) {
            return
        }
        if(isEnabled) normalTextColor else disabledTextColor?.let { setTextColor(it) }
    }

    private fun init() {
        normalTextColor?.let {
            setTextColor(it)
        }
        updateBackgroundDrawable()
        setOnTouchListener(onTouchListener)
    }

    private fun updateBackgroundDrawable() {
        val normalBackgroundDrawable = GradientDrawable()
        normalBackgroundDrawable.cornerRadius = cornerRadius
        normalBackgroundDrawable.setColor(normalBackgroundColor)
        normalBackgroundDrawable.setStroke(strokeWidth, normalStrokeColor, dashWidth, dashGap)
        normalBackgroundDrawable.shape = shape

        val pressedBackgroundDrawable = GradientDrawable()
        pressedBackgroundDrawable.cornerRadius = cornerRadius
        pressedBackgroundDrawable.setColor(pressedBackgroundColor)
        pressedBackgroundDrawable.setStroke(strokeWidth, pressedStrokeColor, dashWidth, dashGap)
        pressedBackgroundDrawable.shape = shape

        val disabledBackgroundDrawable = GradientDrawable()
        disabledBackgroundDrawable.cornerRadius = cornerRadius
        disabledBackgroundDrawable.setColor(disabledBackgroundColor)
        disabledBackgroundDrawable.setStroke(strokeWidth, disabledStrokeColor, dashWidth, dashGap)
        disabledBackgroundDrawable.shape = shape

        val stateListDrawable = StateListDrawable()
        stateListDrawable.addState(
            intArrayOf(android.R.attr.state_pressed),
            pressedBackgroundDrawable
        )
        stateListDrawable.addState(
            intArrayOf(-android.R.attr.state_enabled),
            disabledBackgroundDrawable
        )
        stateListDrawable.addState(intArrayOf(), normalBackgroundDrawable)

        background = stateListDrawable
    }

    private var onTouchListener = object : OnTouchListener {
        @SuppressLint("ClickableViewAccessibility")
        override fun onTouch(v: View, event: MotionEvent): Boolean {
            if (!isEnabled) return false
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    setTextColor(pressedTextColor)
                }

                MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                    normalTextColor?.let {
                        setTextColor(it)
                    }
                }
            }

            return false
        }
    }

    override fun setEnabled(enabled: Boolean) {
        if(normalTextColor == null || disabledTextColor == null) {
            super.setEnabled(enabled)
            return
        }
        if(enabled) normalTextColor else disabledTextColor?.let { setTextColor(it) }
        super.setEnabled(enabled)
    }
}