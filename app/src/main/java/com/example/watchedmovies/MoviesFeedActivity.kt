package com.example.watchedmovies

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_movies_feed.*
import java.lang.reflect.Type


const val MOVIE_EDIT: Int = 1

class MoviesFeedActivity : AppCompatActivity(R.layout.activity_movies_feed), MovieAdapter.Callback {

    //Лист из фильмов (data class Movie из MovieinfoEditorActivity)
    private var movies : ArrayList<MovieinfoEditorActivity.Movie> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Загружаем данные для элементов списка
        loadMoviesData()

        //Привязываем адаптер к RecyclerView и передаем данные в адаптер
        movie_recycler_view.adapter = MovieAdapter(movies, this)

        //Кнопка с плюсом
        fab.setOnClickListener {
            val intent = Intent(this, MovieinfoEditorActivity::class.java)
            startActivityForResult(intent, MOVIE_EDIT)
        }
    }

    //Выводим и сохраняем данные
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == MOVIE_EDIT){
            if (data != null) {
                movies.add(MovieinfoEditorActivity.Movie(data.getStringExtra("TITLE"), data.getStringExtra("POSITIVE"), data.getStringExtra("NEGATIVE")))
                saveMoviesData() //Сохраняем данные
            }
        }
    }

    private fun loadMoviesData(){
        val sPref = getPreferences(Context.MODE_PRIVATE)
        val json = sPref.getString("MOVIES", null)
        Log.i("ITEM_MOVIE", "Загружено: $json")
        if (json != null) {
            val type: Type = object : TypeToken<ArrayList<MovieinfoEditorActivity.Movie>>() {}.type
            movies = Gson().fromJson(json, type)
        }
    }

    private fun saveMoviesData(){
        val json: String = Gson().toJson(movies)
        Log.i("ITEM_MOVIE", "Сохранено: $json")
        val sPref = getPreferences(Context.MODE_PRIVATE)
        sPref.edit().putString("MOVIES", json).apply()
    }

    private fun clearMoviesData(){
        Log.i("ITEM_MOVIE", "Список очищен")
        val sPref = getPreferences(Context.MODE_PRIVATE)
        sPref.edit().putString("MOVIES", null).apply()
        movies.clear()
    }

    override fun onMovieItemClicked(position: Int) {
        Log.i("ITEM_MOVIE", "onMovieItemClicked: $position")

        //Удаление всех элементов списка из Preferences и коллекции (для отладки)
        if (position == 0){
            clearMoviesData()
            movie_recycler_view.adapter?.notifyDataSetChanged()
        }
    }
}
