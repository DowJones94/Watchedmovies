package com.example.watchedmovies

import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_test.*


class TestActivity : AppCompatActivity() {
    private var x0 = 0F
    private var y0 = 0F
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        //Выводим координаты касаний по FrameLayout и событие action
        /*testFrameLayout.setOnTouchListener OnTouchListener@{ _, event ->
            Log.i("WATCHED", "FrameLayout > X: ${event.x}; Y: ${event.y}; Action: ${MotionEvent.actionToString(event.action)}")
            return@OnTouchListener true
        }*/

        //Находим точку касания, делаем сдвиг View с поправкой на точку касания
        myTextView2.setOnTouchListener onTouchListener@{ _, event ->
            when(event.action){
                MotionEvent.ACTION_DOWN -> {
                    x0 = event.x
                    y0 = event.y
                }
                MotionEvent.ACTION_MOVE -> {
                    myTextView2.translationX += event.x - x0
                    myTextView2.translationY += event.y - y0
                }
            }
            return@onTouchListener true
        }
    }
}