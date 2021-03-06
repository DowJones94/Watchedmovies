package com.example.watchedmovies

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_movies_feed.*
import java.lang.reflect.Type


const val MOVIE_EDIT: Int = 1
const val KEY_MOVIES = "MOVIES"

class MoviesFeedActivity : AppCompatActivity(R.layout.activity_movies_feed), MovieAdapter.Callback {

    //Лист из фильмов (data class Movie из MovieModel)
    private val movies = mutableListOf<Movie>()
    private lateinit var sPref : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sPref = getPreferences(Context.MODE_PRIVATE) //Инициализаруем Shared Preferences
        loadMoviesData() //Загружаем данные для элементов списка

        //Привязываем адаптер к RecyclerView и передаем данные в адаптер
        rvMovie.adapter = MovieAdapter(movies, this)

        //Кнопка с плюсом
        fab.setOnClickListener {
            val intent = Intent(this, MovieinfoEditorActivity::class.java)
            startActivityForResult(intent, MOVIE_EDIT)
        }

        btnTestActivity.setOnClickListener {
            val intent = Intent(this, TestActivity::class.java)
            startActivity(intent)
        }
    }

    //Выводим и сохраняем данные
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == MOVIE_EDIT){
            data?.let {
                movies.add(Movie(it.getStringExtra("TITLE"), it.getStringExtra("POSITIVE"), it.getStringExtra("NEGATIVE")))
                rvMovie.adapter?.notifyDataSetChanged() //уведомляем адаптер об изменении списка
                saveMoviesData() //Сохраняем данные
            }
        }
    }

    private fun loadMoviesData(){
        val json = sPref.getString(KEY_MOVIES, null)
        Log.i("ITEM_MOVIE", "Загружено: $json")
        json?.let { moviesJson->
            val type: Type = object : TypeToken<MutableList<Movie>>() {}.type
            val localMoviesList = Gson().fromJson<MutableList<Movie>>(moviesJson, type)
            movies.clear() //Очищаем список перед добавлением в него восстановленных элементов
            movies.addAll(localMoviesList)
        }
    }

    private fun saveMoviesData(){
        val json = Gson().toJson(movies)
        Log.i("ITEM_MOVIE", "Сохранено: $json")
        sPref.edit().putString(KEY_MOVIES, json).apply()
    }

    private fun clearMoviesData(){
        Log.i("ITEM_MOVIE", "Список очищен")
        sPref.edit().remove(KEY_MOVIES).apply()
        movies.clear()
    }

    override fun onMovieItemClicked(position: Int) {
        Log.i("ITEM_MOVIE", "onMovieItemClicked: $position")

        //Удаление всех элементов списка из Preferences и коллекции (для отладки)
        if (position == 0){
            clearMoviesData()
            rvMovie.adapter?.notifyDataSetChanged()
        }
    }
}
