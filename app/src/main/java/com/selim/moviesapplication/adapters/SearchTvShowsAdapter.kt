package com.selim.moviesapplication.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.selim.moviesapplication.model.DTO.Movie
import com.selim.moviesapplication.R
import com.selim.moviesapplication.databinding.MovieItemLayoutBinding
import com.selim.moviesapplication.model.DTO.TvShow
import com.selim.moviesapplication.utils.Constants
import java.text.DecimalFormat

class SearchTvShowsAdapter:RecyclerView.Adapter<SearchTvShowsAdapter.MovieViewHolder>() {
    private val moviesList =ArrayList<TvShow>()
    lateinit var onTvShowItemClicked :((TvShow)->Unit)
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
            val currentMovie: TvShow =moviesList[position]
            val decimalFormat = DecimalFormat("#.#")
            val vote = decimalFormat.format(currentMovie.voteAverage)
            val posterUrl = Constants.POSTER_BASE_URL+currentMovie.posterPath
            holder.binding.apply {
                tvPopularMovieMovieName.text = currentMovie.originalName
                tvPopularMovieRating.text = vote.toString()
                Glide.with(holder.itemView).load(posterUrl).into(ivPopularMovieItemPoster)
            }
            holder.itemView.setOnClickListener {
                onTvShowItemClicked.invoke(currentMovie)
            }
        }
    }

    fun setData(newList:ArrayList<TvShow>){
        this.moviesList.clear()
        this.moviesList.addAll(newList)
        notifyDataSetChanged()
    }
}