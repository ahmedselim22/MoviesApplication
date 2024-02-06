//package com.selim.moviesapplication.paging
//
//import android.util.Log
//import com.selim.moviesapplication.utils.Constants
//import androidx.paging.PagingSource
//import androidx.paging.PagingState
//import com.selim.moviesapplication.data.remote.ApiService
//import com.selim.moviesapplication.model.DTO.Movie
//import com.selim.moviesapplication.model.DTO.Movies
//import retrofit2.HttpException
//import java.io.IOException
//import javax.inject.Inject
//
//private const val STARTING_PAGE = 1
//
//class MoviePagingSource @Inject constructor(private val apiService: ApiService) :
//    PagingSource<Int, Movie>() {
//    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
//        TODO("Not yet implemented")
//    }
//
//    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
//        val page = params.key ?: STARTING_PAGE
//        return try {
//            val response = apiService.getPopularMoviesList(Constants.API_KEY, "en-US", page)
//            val movies = response.body()?.movies as List<Movie>
//            Log.d("here paging ", movies.toString())
//            LoadResult.Page(
//                data = movies,
//                prevKey = if (page == STARTING_PAGE) null else page - 1,
//                nextKey = if (movies.isEmpty()) null else page + 1
//            )
//        } catch (exception: IOException) {
//            return LoadResult.Error(exception)
//        } catch (exception: HttpException) {
//            return LoadResult.Error(exception)
//        }
//    }
//
//}