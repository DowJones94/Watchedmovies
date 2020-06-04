package com.example.watchedmovies

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_movies_feed.*


const val REQUEST_CODE_MOVIE_EDIT: Int = 1

class MoviesFeedActivity : AppCompatActivity(R.layout.activity_movies_feed) {

    //Лист из фильмов (data class Movie из MovieinfoEditorActivity)
    private val movies : ArrayList<MovieinfoEditorActivity.Movie> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setMoviesData() //Задаем начальные данные для элементов списка

        //Привязываем адаптер к RecyclerView и передаем данные в адаптер
        val recyclerView = findViewById<View>(R.id.movie_recycler_view) as RecyclerView
        recyclerView.adapter = MovieAdapter(this, movies)

        //Кнопка с плюсом
        fab.setOnClickListener {
            val intent = Intent(this, MovieinfoEditorActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE_MOVIE_EDIT)
        }
    }

    private fun setMoviesData(){
        for(i in 1..100){
            movies.add(MovieinfoEditorActivity.Movie("Заголовок $i", "Не понравилось $i", "Понравилось $i"))
        }
    }
}
