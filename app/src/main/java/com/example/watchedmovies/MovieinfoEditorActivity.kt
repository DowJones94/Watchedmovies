package com.example.watchedmovies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MovieinfoEditorActivity : AppCompatActivity(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val toast = Toast.makeText(applicationContext, "Добавлено", Toast.LENGTH_SHORT)
        btnAddMovie.setOnClickListener { toast.show() }
    }
}