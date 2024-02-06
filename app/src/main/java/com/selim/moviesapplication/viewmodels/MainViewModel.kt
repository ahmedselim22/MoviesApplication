package com.selim.moviesapplication.viewmodels

import androidx.lifecycle.*
import com.selim.moviesapplication.model.DTO.Movie
import com.selim.moviesapplication.model.DTO.TvShow
import com.selim.moviesapplication.repository.MovieRepository
import com.selim.moviesapplication.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val movieRepository:MovieRepository
):ViewModel() {
//    val moviesList :Flow<PagingData<Movie>> = movieRepository.getPopularMoviesList().cachedIn(viewModelScope)
    private var pageNum :Int =0
    val popularMovies = MutableLiveData<List<Movie?>?>()
    val topRatedMovies = MutableLiveData<List<Movie?>?>()
    val upComingMovies = MutableLiveData<List<Movie?>?>()
    val trendingMovies = MutableLiveData<List<Movie?>?>()
    val searchedMovies = MutableLiveData<List<Movie?>?>()
    val searchedTvShows = MutableLiveData<List<TvShow?>?>()
    val popularTvShows = MutableLiveData<List<TvShow?>?>()
    val trendingTvShows = MutableLiveData<List<TvShow?>?>()
    val topRatedTvShows = MutableLiveData<List<TvShow?>?>()
    val onTheAirTvShows = MutableLiveData<List<TvShow?>?>()

    init {
        getPopularMovies()
        getTopRatedMovies()
        getUpComingMovies()
        getTrendingMovies()
        getPopularTvShows()
        getTrendingTvShows()
        getTopRatedTvShows()
        getOnTheAirTvShows()
    }

    fun getPopularMovies(){
        viewModelScope.launch(Dispatchers.IO) {
            val response = movieRepository.getPopularMovies(Constants.API_KEY,"en-US", pageNum+1).body()?.movies
            popularMovies.postValue(response)
        }
    }
    fun getTopRatedMovies(){
        viewModelScope.launch(Dispatchers.IO) {
            val response = movieRepository.getTopRatedMovies(Constants.API_KEY,"en-US", ++pageNum).body()?.movies
            topRatedMovies.postValue(response)
        }
    }

    fun getUpComingMovies(){
        viewModelScope.launch(Dispatchers.IO) {
            val response = movieRepository.getUpComingMovies(Constants.API_KEY,"en-US", ++pageNum).body()?.movies
            upComingMovies.postValue(response)
        }
    }
    fun getTrendingMovies(){
        viewModelScope.launch(Dispatchers.IO) {
            val response = movieRepository.getTrendingMovies(Constants.API_KEY,"en-US", ++pageNum).body()?.movies
            trendingMovies.postValue(response)
        }
    }

    fun getSearchedMovies(query:String){
        viewModelScope.launch(Dispatchers.IO) {
            delay(200)
            val response = movieRepository.getSearchedMovies(query,Constants.API_KEY,"en-US",++pageNum).body()?.movies
            searchedMovies.postValue(response)
        }
    }

    fun getPopularTvShows(){
        viewModelScope.launch(Dispatchers.IO) {
            val response = movieRepository.getPopularTvShows(Constants.API_KEY,"en-US",++pageNum).body()?.tvShows
            popularTvShows.postValue(response)
        }
    }
    fun getTrendingTvShows(){
        viewModelScope.launch(Dispatchers.IO) {
            val response = movieRepository.getTrendingTvShows(Constants.API_KEY,"en-US",++pageNum).body()?.tvShows
            trendingTvShows.postValue(response)
        }
    }
    fun getTopRatedTvShows(){
        viewModelScope.launch(Dispatchers.IO) {
            val response = movieRepository.getTopRatedTvShows(Constants.API_KEY,"en-US",++pageNum).body()?.tvShows
            topRatedTvShows.postValue(response)
        }
    }
    fun getOnTheAirTvShows(){
        viewModelScope.launch(Dispatchers.IO) {
            val response = movieRepository.getOnTheAirTvShows(Constants.API_KEY,"en-US",++pageNum).body()?.tvShows
            onTheAirTvShows.postValue(response)
        }
    }
    fun getSearchedTvShows(query: String){
        viewModelScope.launch(Dispatchers.IO) {
            delay(200)
            val response = movieRepository.getSearchedTvShows(query,Constants.API_KEY,false,"en-US",++pageNum).body()?.tvShows
            searchedTvShows.postValue(response)
        }
    }

}