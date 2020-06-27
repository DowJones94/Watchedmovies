package com.example.watchedmovies

import android.animation.ValueAnimator
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.doOnLayout
import androidx.core.widget.doAfterTextChanged
import kotlinx.android.synthetic.main.activity_editor.*


class MovieinfoEditorActivity : AppCompatActivity(R.layout.activity_editor) {

    private val animator = ValueAnimator.ofFloat(0.0F, 1.0F).also {
        it.duration = 1000
        it.interpolator = AccelerateDecelerateInterpolator()
        it.addUpdateListener {
            val animVal = it.animatedValue as Float
            val targetTranslationX = 0
            etMovieTitle.alpha = 0.2F + 0.8F*animVal
            etMovieTitle.translationX += (targetTranslationX - etMovieTitle.translationX) * animVal
            etLike.alpha = 0.2F + 0.8F*animVal
            etLike.translationX += (targetTranslationX - etLike.translationX) * animVal
            etDislike.alpha = 0.2F + 0.8F*animVal
            etDislike.translationX += (targetTranslationX - etDislike.translationX) * animVal
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setViewSettingsForAnimation()
        animator.start()

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

    private fun setViewSettingsForAnimation(){
        etMovieTitle.alpha = 0.2F
        etMovieTitle.doOnLayout {
            it.translationX = -it.width.toFloat()
        }
        etLike.alpha = 0.2F
        etLike.doOnLayout {
            it.translationX = -it.width.toFloat()
        }
        etDislike.alpha = 0.2F
        etDislike.doOnLayout {
            it.translationX = -it.width.toFloat()
        }
    }
}