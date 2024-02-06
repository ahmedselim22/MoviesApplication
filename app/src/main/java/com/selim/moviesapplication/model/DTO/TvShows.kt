package com.selim.moviesapplication.model.DTO


import com.google.gson.annotations.SerializedName

data class TvShows(
    @SerializedName("page")
    val page: Int = 0,
    @SerializedName("results")
    val tvShows: List<TvShow> = listOf(),
    @SerializedName("total_pages")
    val totalPages: Int = 0,
    @SerializedName("total_results")
    val totalResults: Int = 0
)