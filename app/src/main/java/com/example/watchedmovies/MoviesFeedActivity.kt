package com.example.watchedmovies

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

import kotlinx.android.synthetic.main.activity_movies_feed.*

const val REQUEST_CODE_MOVIE_EDIT: Int = 1

class MoviesFeedActivity : AppCompatActivity(R.layout.activity_movies_feed) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fab.setOnClickListener {
            val intent = Intent(this, MovieinfoEditorActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE_MOVIE_EDIT)
        }
    }
}
