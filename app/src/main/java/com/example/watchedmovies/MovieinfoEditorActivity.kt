package com.example.watchedmovies

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import kotlinx.android.synthetic.main.activity_editor.*


class MovieinfoEditorActivity : AppCompatActivity(R.layout.activity_editor) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        btnAddMovie.setOnClickListener {
            if (validateFields()) {
                val movie = Movie(etMovieTitle.text.trim().toString(), etLike.text.trim().toString(), etDislike.text.trim().toString())
                val intent = Intent()
                intent.putExtra("TITLE", movie.title)
                intent.putExtra("POSITIVE", movie.positiveComment)
                intent.putExtra("NEGATIVE", movie.negativeComment)
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
            etMovieTitle.error = resources.getString(R.string.et_required_error)
            bIsFilled = false
        }

        if (etLike.text.trim().isEmpty()) {
            etLike.error = resources.getString(R.string.et_required_error)
            bIsFilled = false
        }

        if (etDislike.text.trim().isEmpty()) {
            etDislike.error = resources.getString(R.string.et_required_error)
            bIsFilled = false
        }

        return bIsFilled
    }
}