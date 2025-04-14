package com.example.apper.ui.custom

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.util.Log

class BasicShapesView : CustomViewScaffold {
    private val paint = Paint()
    private var lineXLeft = 0f
    private var lineXRight = 0f
    private var lineYPos = 0f
    private var lineHeight = 0f
    private val rectangleRect = RectF()

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
        val lineMarginHorizontal = dpToPx(LINE_MARGIN_HORIZONTAL_DP)
        lineXLeft = lineMarginHorizontal
        lineXRight = w - lineMarginHorizontal
        lineYPos = h * LINE_Y_POS_FRACTION
        lineHeight = dpToPx(LINE_HEIGHT_DP)
        val rectangleMarginHorizontal = dpToPx(RECT__MARGIN_HORIZONTAL_DP)
        val rectangleWidth = w - 2 * rectangleMarginHorizontal
        val rectangleHeight = rectangleWidth / 2
        val rectangleTop = (h - rectangleHeight) / 2
        val rectangleBottom = (h + rectangleHeight) / 2
        Log.i("onSizeChanged: left:",""+rectangleMarginHorizontal)
        Log.i("onSizeChanged: top:",""+rectangleTop)
        Log.i("onSizeChanged: right:",""+rectangleMarginHorizontal + rectangleWidth)
        Log.i("onSizeChanged: bottom:",""+rectangleBottom)
        rectangleRect.set(
            rectangleMarginHorizontal,
            rectangleTop,
            rectangleMarginHorizontal + rectangleWidth,
            rectangleBottom
        )
    }

    //Will be called when this view is redrawn and itself on the screen
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        paint.color = Color.RED
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = dpToPx(LINE_HEIGHT_DP)
        //canvas.drawLine(lineXLeft, lineYPos, lineXRight, lineYPos, paint)
        canvas.drawRect(rectangleRect, paint)
    }

    companion object {
        const val LINE_HEIGHT_DP = 10f
        const val LINE_MARGIN_HORIZONTAL_DP = 20f
        const val LINE_Y_POS_FRACTION = 0.3f
        const val RECT__MARGIN_HORIZONTAL_DP = 20f
    }
}