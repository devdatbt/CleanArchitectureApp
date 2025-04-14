package com.example.apper.ui.custom

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View

open class CustomViewScaffold : View {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attr: AttributeSet?) : super(context, attr)
    constructor(context: Context?, attr: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attr,
        defStyleAttr
    )
    constructor(
        context: Context?,
        attr: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attr, defStyleAttr, defStyleRes)

    //Will be called when this view change its size
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
    }

    //Will be called when this view is redrawn and itself on the screen
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
    }

    fun dpToPx(dp: Float): Float {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dp,
            context.resources.displayMetrics
        )
    }
}