package com.example.watchedmovies

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.res.use
import com.example.watchedmovies.extensions.argb
import com.example.watchedmovies.extensions.dp

class MyTextView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatTextView(context, attrs, defStyleAttr)/*, Choreographer.FrameCallback*/ {

    companion object {
        val DEFAULT_COLOR = 0xFFFF0000.argb // red argb
        val DEFAULT_BORDER_RADIUS = 5F.dp
    }

    private val emptyPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val backPaint = Paint(emptyPaint).also {
        it.color = backColor
        it.style = Paint.Style.FILL
    }

    private var backColor = DEFAULT_COLOR
        set(value) {
            field = value
            backPaint.color = backColor
        }
    private var borderRadius = DEFAULT_BORDER_RADIUS

    init {
        getContext().obtainStyledAttributes(attrs, R.styleable.MyTextView, defStyleAttr, 0).use {
            backColor = it.getColor(
                R.styleable.MyTextView_backColor,
                DEFAULT_COLOR
            )
            borderRadius = it.getDimension(
                R.styleable.MyTextView_borderRadius,
                DEFAULT_BORDER_RADIUS
            )
        }
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawRoundRect(0F.dp, 0F.dp, width.toFloat(), height.toFloat(), borderRadius, borderRadius, backPaint)
        super.onDraw(canvas)
    }
}