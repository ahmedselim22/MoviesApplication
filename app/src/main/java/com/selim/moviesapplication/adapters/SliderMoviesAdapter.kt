package com.selim.moviesapplication.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bumptech.glide.Glide
import com.selim.moviesapplication.model.DTO.Movie
import com.selim.moviesapplication.R
import com.selim.moviesapplication.databinding.SliderItemBinding
import com.selim.moviesapplication.model.entities.MovieEntity
import com.selim.moviesapplication.utils.Constants
import com.smarteist.autoimageslider.SliderViewAdapter
import java.text.DecimalFormat

class SliderMoviesAdapter:SliderViewAdapter<SliderMoviesAdapter.SliderViewHolder>() {
    private val moviesList =ArrayList<Movie>()
    lateinit var onMovieItemClicked:((Movie)->Unit)
    inner class SliderViewHolder(itemView: View):ViewHolder(itemView){
        val binding = SliderItemBinding.bind(itemView)
    }

    override fun onBindViewHolder(holder: SliderViewHolder, position: Int) {
        if (moviesList.isNotEmpty()){
            val currentMovie =moviesList[position]
            val decimalFormat = DecimalFormat("#.#")
            val vote = decimalFormat.format(currentMovie.voteAverage)
            val posterUrl = Constants.POSTER_BASE_URL+currentMovie.posterPath
            holder.binding.apply {
                tvSliderItemRating.text = vote.toString()
                Glide.with(holder.itemView).load(posterUrl).centerCrop().into(ivSliderItemImage)
            }
            holder.itemView.setOnClickListener {
                onMovieItemClicked.invoke(currentMovie)
            }
        }
    }

    fun setData(newList:ArrayList<Movie>){
        this.moviesList.clear()
        this.moviesList.addAll(newList)
        notifyDataSetChanged()
    }

    override fun getCount(): Int {
        return moviesList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?): SliderViewHolder {
        val view = LayoutInflater.from(parent!!.context).inflate(R.layout.slider_item,parent,false)
        return SliderViewHolder(view)
    }

}