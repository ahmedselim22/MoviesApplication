package com.selim.moviesapplication.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.selim.moviesapplication.R
import com.selim.moviesapplication.databinding.MovieItemLayoutBinding
import com.selim.moviesapplication.model.DTO.TvShow
import com.selim.moviesapplication.model.entities.TvShowEntity
import com.selim.moviesapplication.utils.Constants
import java.text.DecimalFormat

class TopRatedTvShowsAdapter:RecyclerView.Adapter<TopRatedTvShowsAdapter.TvShowsViewHolder>() {
    private val tvShowsList =ArrayList<TvShow>()
    lateinit var onTvShowItemClicked:((TvShow)->Unit)
    inner class TvShowsViewHolder(itemView: View):ViewHolder(itemView){
        val binding = MovieItemLayoutBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item_layout,parent,false)
        return TvShowsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return tvShowsList.size
    }

    override fun onBindViewHolder(holder: TvShowsViewHolder, position: Int) {
        if (tvShowsList.isNotEmpty()){
            val currentTvShow: TvShow =tvShowsList[position]
            val decimalFormat = DecimalFormat("#.#")
            val vote = decimalFormat.format(currentTvShow.voteAverage)
            val posterUrl = Constants.POSTER_BASE_URL+currentTvShow.posterPath
            holder.binding.apply {
                tvPopularMovieMovieName.text = currentTvShow.originalName
                tvPopularMovieRating.text = vote.toString()
                Glide.with(holder.itemView).load(posterUrl).into(ivPopularMovieItemPoster)
            }
            holder.itemView.setOnClickListener {
                onTvShowItemClicked.invoke(currentTvShow)
            }
        }
    }

    fun setData(newList:ArrayList<TvShow>){
        this.tvShowsList.addAll(newList)
        notifyDataSetChanged()
    }

}