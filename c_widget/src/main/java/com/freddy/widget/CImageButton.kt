package com.freddy.widget

import android.content.Context
import android.graphics.drawable.Drawable
import android.graphics.drawable.StateListDrawable
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageButton

class CImageButton : AppCompatImageButton {

    private var normalImageRes: Drawable? = null
    private var pressedImageRes: Drawable? = null
    private var disabledImageRes: Drawable? = null

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        val array = context.obtainStyledAttributes(attrs, R.styleable.CImageButton, defStyleAttr, 0)
        normalImageRes = array.getDrawable(R.styleable.CImageButton_cib_normalImageRes)
        pressedImageRes = array.getDrawable(R.styleable.CImageButton_cib_pressedImageRes)
        disabledImageRes = array.getDrawable(R.styleable.CImageButton_cib_disabledImageRes)
        array.recycle()
        init()
    }

    private fun init() {
        updateImageDrawable()
    }

    private fun updateImageDrawable() {
        val stateListDrawable = StateListDrawable()
        stateListDrawable.addState(
            intArrayOf(android.R.attr.state_pressed),
            pressedImageRes
        )
        stateListDrawable.addState(
            intArrayOf(-android.R.attr.state_enabled),
            disabledImageRes
        )
        stateListDrawable.addState(intArrayOf(), normalImageRes)

        background = stateListDrawable
    }

    override fun setEnabled(enabled: Boolean) {
        if(enabled) updateImageDrawable() else background = disabledImageRes
        super.setEnabled(enabled)
    }
}