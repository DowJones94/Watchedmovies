package com.example.watchedmovies

import android.os.Bundle
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity

import kotlinx.android.synthetic.main.activity_movies_feed.*

class MoviesFeedActivity : AppCompatActivity(R.layout.activity_movies_feed) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val toast = Toast.makeText(applicationContext, "FAB", Toast.LENGTH_SHORT)
        fab.setOnClickListener { toast.show() }
    }

}
