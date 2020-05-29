package com.example.watchedmovies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_editor.*

class MovieinfoEditorActivity : AppCompatActivity(R.layout.activity_editor) {

    data class Movie(
        val title: String,
        val positiveComment: String,
        val negativeComment: String
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        btnAddMovie.setOnClickListener { checkEditText() }
    }

    private fun checkEditText(){

        if (etMovieTitle.text.trim().isEmpty()) {
            etMovieTitle.error = "Обязательное поле"
        }

        if (etLike.text.trim().isEmpty()) {
            etLike.error = "Обязательное поле"
        }

        if (etDislike.text.trim().isEmpty()) {
            etDislike.error = "Обязательное поле"
        }

    }
}