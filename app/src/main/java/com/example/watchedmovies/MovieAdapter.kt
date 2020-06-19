package com.example.watchedmovies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.movie_item.view.*


class MovieAdapter(val movies : List<MovieinfoEditorActivity.Movie>, val callback : Callback) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView: View = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount() = movies.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener { callback.onMovieItemClicked(adapterPosition) }
        }
        fun bind() {
            val movie = movies[adapterPosition]
            itemView.tvTitle.text = movie.title
            itemView.tvDislike.text = movie.negativeComment
            itemView.tvLike.text = movie.positiveComment
        }
    }

    interface Callback {
        fun onMovieItemClicked(position: Int)
    }
}