package com.selim.moviesapplication.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.selim.moviesapplication.model.DTO.Movie
import com.selim.moviesapplication.R
import com.selim.moviesapplication.databinding.MovieItemLayoutBinding
import com.selim.moviesapplication.model.entities.MovieEntity
import com.selim.moviesapplication.utils.Constants
import java.text.DecimalFormat

class TrendingMoviesAdapter:RecyclerView.Adapter<TrendingMoviesAdapter.MovieViewHolder>() {
    private val moviesList =ArrayList<Movie>()
    lateinit var onMovieItemClicked:((Movie)->Unit)
    inner class MovieViewHolder(itemView: View):ViewHolder(itemView){
        val binding = MovieItemLayoutBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item_layout,parent,false)
        return MovieViewHolder(view)
    }

    override fun getItemCount(): Int {
        return moviesList.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        if (moviesList.isNotEmpty()){
            val currentMovie: Movie =moviesList[position]
            val decimalFormat = DecimalFormat("#.#")
            val vote = decimalFormat.format(currentMovie.voteAverage)
            val posterUrl = Constants.POSTER_BASE_URL+currentMovie.posterPath
            holder.binding.apply {
                tvPopularMovieMovieName.text = currentMovie.originalTitle
                tvPopularMovieRating.text = vote.toString()
                Glide.with(holder.itemView).load(posterUrl).into(ivPopularMovieItemPoster)
            }
            holder.itemView.setOnClickListener {
                onMovieItemClicked.invoke(currentMovie)
            }
        }
    }

    fun setData(newList:ArrayList<Movie>){
        this.moviesList.addAll(newList)
        notifyDataSetChanged()
    }

}