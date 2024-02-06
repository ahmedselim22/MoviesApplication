package com.selim.moviesapplication.ui.activities

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.selim.moviesapplication.databinding.ActivityTvShowDetailsBinding
import com.selim.moviesapplication.model.DTO.TvShow
import com.selim.moviesapplication.utils.Constants

class TvShowDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTvShowDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTvShowDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.statusBarColor = Color.TRANSPARENT
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN

        val tvShow: TvShow =intent.getSerializableExtra("tvShow") as TvShow
        tvShow.let { tvShow->
            binding.collapsingToolBarTvShowDetails.title = tvShow.originalName
            binding.tvTvShowDetailsName.text = tvShow.originalName
            if (!tvShow.adult) {
                binding.tvTvShowDetailsFamilyWatch.text = "suitable"
            }
            else{
                binding.tvTvShowDetailsFamilyWatch.text = "not suitable"
            }
            binding.tvTvShowDetailsVoteCount.text =tvShow.voteCount.toString()
            binding.tvTvShowDetailsVoteAverage.text =tvShow.voteAverage.toString()
            binding.tvTvShowDetailsOverView.text =tvShow.overview
            binding.tvTvShowDetailsPopularity.text =tvShow.popularity.toString()
            binding.tvTvShowDetailsOriginalLanguage.text =tvShow.originalLanguage
            binding.tvTvShowDetailsFirstAirDate.text = tvShow.firstAirDate
            val imageUrl = Constants.POSTER_BASE_URL+tvShow.backdropPath
            Glide.with(this).load(imageUrl).into(binding.ivTvShowDetailsAppBar)
        }
    }
}