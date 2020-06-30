package com.example.watchedmovies

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.Choreographer
import android.view.View
import android.view.animation.AccelerateInterpolator
import androidx.core.content.res.use
import com.example.watchedmovies.extensions.dp
import com.example.watchedmovies.extensions.rgb

class TempView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : View(context, attrs, defStyleAttr, defStyleRes)/*, Choreographer.FrameCallback*/ {

    companion object {
        val DEFAULT_LINE_COLOR = 0xFFFFFF.rgb // white rgb
    }

    private val emptyPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val linePaint = Paint(emptyPaint).also {
        it.color = lineColor
    }

    private var lineColor = DEFAULT_LINE_COLOR
        set(value) {
            field = value
            linePaint.color = lineColor
        }

    init {
        getContext().obtainStyledAttributes(attrs, R.styleable.TempView, defStyleAttr, defStyleRes).use {
            lineColor = it.getColor(
                R.styleable.TempView_lineColor,
                DEFAULT_LINE_COLOR
            )
        }
        //Раскомментишь тогда, когда будем пользоваться анимациями
        //        Choreographer.getInstance().postFrameCallback(this) //Запрос следующего кадра (вызов doFrame)
    }

//Раскомментишь тогда, когда будем пользоваться анимациями
//    override fun doFrame(frameTimeNanos: Long) { //Получили кадр
//        invalidate() //Перерисовали (вызов onDraw)
//        Choreographer.getInstance().postFrameCallback(this) //Запрос следующего кадра (вызов doFrame)
//    }

    private var animVal = 0F
    private val animator = ValueAnimator.ofFloat(0.0F, 1.0F).also {
        it.duration = 450
        it.interpolator = AccelerateInterpolator()
        it.repeatMode = ValueAnimator.REVERSE
        it.repeatCount = ValueAnimator.INFINITE
        it.addUpdateListener {
            animVal = it.animatedValue as Float
        }
        it.start()
    }

    override fun onDraw(canvas: Canvas) {
        linePaint.let {
            it.style = Paint.Style.STROKE
            it.strokeWidth = 2F.dp
            it.color = 0x6200EE.rgb
        }
        canvas.drawCircle(width / 2f,height / 2f, (50F - 10F*animVal).dp, linePaint)
        linePaint.style = Paint.Style.FILL
        canvas.drawCircle(width / 2f,height / 2f, (35F*animVal).dp, linePaint)
        invalidate()
    }
}