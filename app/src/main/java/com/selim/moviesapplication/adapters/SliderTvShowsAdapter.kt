package com.selim.moviesapplication.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bumptech.glide.Glide
import com.selim.moviesapplication.R
import com.selim.moviesapplication.databinding.SliderItemBinding
import com.selim.moviesapplication.model.DTO.TvShow
import com.selim.moviesapplication.model.entities.TvShowEntity
import com.selim.moviesapplication.utils.Constants
import com.smarteist.autoimageslider.SliderViewAdapter
import java.text.DecimalFormat

class SliderTvShowsAdapter:SliderViewAdapter<SliderTvShowsAdapter.SliderViewHolder>() {
    private val tvShowsList =ArrayList<TvShow>()
    lateinit var onTvShowItemClicked:((TvShow)->Unit)
    inner class SliderViewHolder(itemView: View):ViewHolder(itemView){
        val binding = SliderItemBinding.bind(itemView)
    }

    override fun onBindViewHolder(holder: SliderViewHolder, position: Int) {
        if (tvShowsList.isNotEmpty()){
            val currentTvShow =tvShowsList[position]
            val decimalFormat = DecimalFormat("#.#")
            val vote = decimalFormat.format(currentTvShow.voteAverage)
            val posterUrl = Constants.POSTER_BASE_URL+currentTvShow.posterPath
            holder.binding.apply {
                tvSliderItemRating.text = vote.toString()
                Glide.with(holder.itemView).load(posterUrl).centerCrop().into(ivSliderItemImage)
            }
            holder.itemView.setOnClickListener {
                onTvShowItemClicked.invoke(currentTvShow)
            }
        }
    }

    fun setData(newList:ArrayList<TvShow>){
        this.tvShowsList.clear()
        this.tvShowsList.addAll(newList)
        notifyDataSetChanged()
    }

    override fun getCount(): Int {
        return tvShowsList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?): SliderViewHolder {
        val view = LayoutInflater.from(parent!!.context).inflate(R.layout.slider_item,parent,false)
        return SliderViewHolder(view)
    }

}