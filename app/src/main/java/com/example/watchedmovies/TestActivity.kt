package com.example.watchedmovies

import android.os.Bundle
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_test.*


class TestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        testFrameLayout.setOnTouchListener OnTouchListener@{ _, event ->
            val x = String.format("%.2f", event.x)
            val y = String.format("%.2f", event.y)

            myTextView2.text = "X: $x Y: $y"
            return@OnTouchListener true
        }
    }
}