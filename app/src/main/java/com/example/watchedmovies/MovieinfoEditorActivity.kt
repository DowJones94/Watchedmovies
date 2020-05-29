package com.example.watchedmovies

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import kotlinx.android.synthetic.main.activity_editor.*


class MovieinfoEditorActivity : AppCompatActivity(R.layout.activity_editor) {

    data class Movie(
        val title: String,
        val positiveComment: String,
        val negativeComment: String
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        btnAddMovie.setOnClickListener {
            if (validateFields()) {
                val intent = Intent()
                intent.putExtra("MOVIE_TITLE", etMovieTitle.text.trim().toString())
                intent.putExtra("MOVIE_LIKE", etLike.text.trim().toString())
                intent.putExtra("MOVIE_DISLIKE", etDislike.text.trim().toString())
                setResult(Activity.RESULT_OK, intent)
                finish()
            }
        }

        /* Сброс ошибок */
        etMovieTitle.doAfterTextChanged { etMovieTitle.error = null }
        etLike.doAfterTextChanged { etLike.error = null }
        etDislike.doAfterTextChanged { etDislike.error = null }
    }

    private fun validateFields() : Boolean {

        var bIsFilled = true

        if (etMovieTitle.text.trim().isEmpty()) {
            etMovieTitle.error = "Обязательное поле"
            bIsFilled = false
        }

        if (etLike.text.trim().isEmpty()) {
            etLike.error = "Обязательное поле"
            bIsFilled = false
        }

        if (etDislike.text.trim().isEmpty()) {
            etDislike.error = "Обязательное поле"
            bIsFilled = false
        }

        return bIsFilled
    }
}