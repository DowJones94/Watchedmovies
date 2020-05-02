package com.example.watchedmovies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Поиск вьюх
        val testMainTextView: TextView = testMainTextView
        val watchedMovieButton: Button = watched_movies_button
        val addMovieButton: Button = add_movie_button

        addMovieButton.setOnClickListener { testMainTextView.text = "Добавить" }
        watchedMovieButton.setOnClickListener { testMainTextView.text = "Просмотренные" }
    }
}
