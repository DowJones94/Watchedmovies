package com.example.watchedmovies

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import android.view.animation.AccelerateInterpolator
import androidx.core.content.res.use
import com.example.watchedmovies.extensions.argb

import com.example.watchedmovies.extensions.dp

class TempView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : View(context, attrs, defStyleAttr, defStyleRes)/*, Choreographer.FrameCallback*/ {

    companion object {
        val DEFAULT_COLOR = 0xFFFF0000.argb // red argb
        val DEFAULT_WIDTH = 1F.dp // 1 dp
    }

    private val emptyPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val circlePaint = Paint(emptyPaint).also {
        it.color = circleColor
        it.style = Paint.Style.FILL
    }
    private val ringPaint = Paint(emptyPaint).also {
        it.color = ringColor
        it.strokeWidth = ringWidth
        it.style = Paint.Style.STROKE
    }

    private var circleColor = DEFAULT_COLOR
        set(value) {
            field = value
            circlePaint.color = circleColor
        }
    private var ringColor = DEFAULT_COLOR
        set(value) {
            field = value
            ringPaint.color = ringColor
        }
    private var ringWidth = DEFAULT_WIDTH
        set(value) {
            field = value
            ringPaint.strokeWidth = ringWidth
        }

    private var animVal = 0F
    private val animator = ValueAnimator.ofFloat(0.0F, 1.0F).also {
        it.duration = 450
        it.interpolator = AccelerateInterpolator()
        it.repeatMode = ValueAnimator.REVERSE
        it.repeatCount = ValueAnimator.INFINITE
        it.addUpdateListener { anim ->
            animVal = anim.animatedValue as Float
            invalidate()
        }
    }

    init {
        getContext().obtainStyledAttributes(attrs, R.styleable.TempView, defStyleAttr, defStyleRes).use {
            circleColor = it.getColor(
                R.styleable.TempView_circleColor,
                DEFAULT_COLOR
            )
            ringColor = it.getColor(
                R.styleable.TempView_ringColor,
                DEFAULT_COLOR
            )
            ringWidth = it.getDimension(
                R.styleable.TempView_ringWidth,
                DEFAULT_WIDTH
            )
        }
        animator.start()
        //Раскомментишь тогда, когда будем пользоваться анимациями
        //        Choreographer.getInstance().postFrameCallback(this) //Запрос следующего кадра (вызов doFrame)
    }

//Раскомментишь тогда, когда будем пользоваться анимациями
//    override fun doFrame(frameTimeNanos: Long) { //Получили кадр
//        invalidate() //Перерисовали (вызов onDraw)
//        Choreographer.getInstance().postFrameCallback(this) //Запрос следующего кадра (вызов doFrame)
//    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawCircle(width / 2f,height / 2f, ((50F - 10F*animVal).dp + ringWidth/2), ringPaint)
        canvas.drawCircle(width / 2f,height / 2f, (35F*animVal).dp, circlePaint)
    }
}