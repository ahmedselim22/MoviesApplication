package com.selim.moviesapplication.ui.activities

import android.R
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.graphics.toColor
import com.bumptech.glide.Glide
import com.selim.moviesapplication.databinding.ActivityMovieDetailsBinding
import com.selim.moviesapplication.model.DTO.Movie
import com.selim.moviesapplication.utils.Constants

class MovieDetailsActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMovieDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.statusBarColor = Color.TRANSPARENT
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN

        val movie: Movie =intent.getSerializableExtra("movie") as Movie
        movie.let { movie->
            binding.collapsingToolBarMovieDetails.title = movie.originalTitle
            binding.tvMovieDetailsName.text = movie.originalTitle
            if (movie.adult ==false) {
                binding.tvMovieDetailsFamilyWatch.text = "suitable"
            }
            else{
                binding.tvMovieDetailsFamilyWatch.text = "not suitable"
            }
            binding.tvMovieDetailsVoteCount.text =movie.voteCount.toString()
            binding.tvMovieDetailsVoteAverage.text =movie.voteAverage.toString()
            binding.tvMovieDetailsOverView.text =movie.overview
            binding.tvMovieDetailsPopularity.text =movie.popularity.toString()
            binding.tvMovieDetailsOriginalLanguage.text =movie.originalLanguage
            binding.tvMovieDetailsReleaseDate.text = movie.releaseDate
            val imageUrl =Constants.POSTER_BASE_URL+movie.backdropPath
            Glide.with(this).load(imageUrl).into(binding.ivMovieDetailsAppBar)
        }
    }
}