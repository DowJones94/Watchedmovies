package com.example.watchedmovies

import android.content.Context
import android.provider.ContactsContract.CommonDataKinds.Phone
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class MovieAdapter(context : Context, movies : List<MovieinfoEditorActivity.Movie>) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    private val inflater = LayoutInflater.from(context)
    private val movies = movies

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView: View = inflater.inflate(R.layout.movie_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movies.get(position)
        holder.tvTitle.text = movie.title
        holder.tvDislike.text = movie.negativeComment
        holder.tvLike.text = movie.positiveComment
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitle : TextView = itemView.findViewById(R.id.tvTitle)
        val tvDislike : TextView = itemView.findViewById(R.id.tvDislike)
        val tvLike : TextView = itemView.findViewById(R.id.tvLike)
    }
}