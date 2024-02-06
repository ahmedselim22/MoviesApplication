package com.selim.moviesapplication.data.remote

import com.selim.moviesapplication.model.DTO.Movie
import com.selim.moviesapplication.model.DTO.Movies
import com.selim.moviesapplication.model.DTO.TvShows
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") api_key:String,
        @Query("language") language: String,
        @Query("page") page:Int,
    ):Response<Movies>

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query("api_key") api_key:String,
        @Query("language") language: String,
        @Query("page") page:Int,
    ):Response<Movies>

    @GET("movie/upcoming")
    suspend fun getOnUpComingMovies(
        @Query("api_key") api_key:String,
        @Query("language") language: String,
        @Query("page") page:Int,
    ):Response<Movies>

    @GET("trending/movie/day")
    suspend fun getTrendingMovies(
        @Query("api_key") api_key:String,
        @Query("language") language: String,
        @Query("page") page:Int,
    ):Response<Movies>

    @GET("search/movie")
    suspend fun getSearchedMovies(
        @Query("query") query:String,
        @Query("api_key") api_key:String,
        @Query("language") language: String,
        @Query("page") page:Int
    ):Response<Movies>

    @GET("tv/popular")
    suspend fun getPopularTVShows(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Response<TvShows>

    @GET("trending/tv/day")
    suspend fun getTrendingTVShows(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Response<TvShows>

    @GET("tv/top_rated")
    suspend fun getTopRatedTVShows(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ) : Response<TvShows>

    @GET("tv/on_the_air")
    suspend fun getOnTheAirTVShows(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ) : Response<TvShows>

        @GET("search/tv")
    suspend fun getSearchedTvShows(
        @Query("query") query: String,
        @Query("api_key") apiKey: String,
        @Query("include_adult") includeAdult: Boolean,
        @Query("language") language: String,
        @Query("page") page: Int
    ) : Response<TvShows>

//
//    @GET("tv/{series_id}")
//    suspend fun getTVShowDetails(
//        @Path("series_id") seriesID: String,
//        @Query("api_key") apiKey: String,
//        @Query("language") language: String
//    ): Response<TVShowDetailsResponse>
//
//    @GET("tv/{series_id}/season/{season_number}")
//    suspend fun getEpisodeDetails(
//        @Path("series_id") seriesID: String,
//        @Path("season_number") season: String,
//        @Query("api_key") apiKey: String,
//        @Query("language") language: String
//    ) : Response<TVShowEpisodeDetailsResponse>
//
//

}