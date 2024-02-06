package com.selim.moviesapplication.repository

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSourceFactory
import com.selim.moviesapplication.data.local.MovieTvShowDao
import com.selim.moviesapplication.model.DTO.Movies
import com.selim.moviesapplication.data.remote.ApiService
import com.selim.moviesapplication.model.DTO.Movie
import com.selim.moviesapplication.model.DTO.TvShows
import com.selim.moviesapplication.model.entities.MovieEntity
import com.selim.moviesapplication.utils.Constants
//import com.selim.moviesapplication.paging.MoviePagingSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject
private var pageNum:Int =1
class MovieRepository @Inject constructor(private val apiService: ApiService , private val dao:MovieTvShowDao) {

    suspend fun getPopularMovies(apiKey:String,language:String,page:Int):Response<Movies>{
        return apiService.getPopularMovies(apiKey,language,page)
    }
    suspend fun getTopRatedMovies(apiKey:String,language:String,page:Int):Response<Movies>{
        return apiService.getTopRatedMovies(apiKey,language,page)
    }
    suspend fun getUpComingMovies(apiKey:String,language:String,page:Int):Response<Movies>{
        return apiService.getOnUpComingMovies(apiKey,language,page)
    }
    suspend fun getTrendingMovies(apiKey:String,language:String,page:Int):Response<Movies>{
        return apiService.getTrendingMovies(apiKey,language,page)
    }
    suspend fun getSearchedMovies(query:String,apiKey:String,language:String,page:Int):Response<Movies>{
        return apiService.getSearchedMovies(query,apiKey,language,page)
    }
    suspend fun getPopularTvShows(apiKey:String,language:String,page:Int):Response<TvShows>{
        return apiService.getPopularTVShows(apiKey,language,page)
    }
    suspend fun getTrendingTvShows(apiKey:String,language:String,page:Int):Response<TvShows>{
        return apiService.getTrendingTVShows(apiKey,language,page)
    }
    suspend fun getTopRatedTvShows(apiKey:String,language:String,page:Int):Response<TvShows>{
        return apiService.getTopRatedTVShows(apiKey,language,page)
    }
    suspend fun getOnTheAirTvShows(apiKey:String,language:String,page:Int):Response<TvShows>{
        return apiService.getOnTheAirTVShows(apiKey,language,page)
    }
    suspend fun getSearchedTvShows(query: String,apiKey:String,includeAdult:Boolean,language:String,page:Int):Response<TvShows>{
        return apiService.getSearchedTvShows(query,apiKey,includeAdult,language,page)
    }

}