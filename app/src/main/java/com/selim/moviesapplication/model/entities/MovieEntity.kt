package com.selim.moviesapplication.model.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Movies")
data class MovieEntity(
    val adult: Boolean=false,
    val backdropPath: String="",
    @PrimaryKey(autoGenerate = true)
    val id: Int =0,
    val originalLanguage: String="",
    val originalTitle: String="",
    val overview: String="",
    val popularity: Double=0.0,
    val posterPath: String="",
    val releaseDate: String="",
    val title: String="",
    val voteAverage: Double,
    val voteCount: Int=0,
    val source:String=""
)
