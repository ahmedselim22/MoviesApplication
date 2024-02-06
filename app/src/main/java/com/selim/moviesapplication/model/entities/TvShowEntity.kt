package com.selim.moviesapplication.model.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "TvShows")
data class TvShowEntity(
    val adult: Boolean,
    val backdropPath: String ,
    val firstAirDate: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int =0,
    val name: String ,
    val originalLanguage: String ,
    val originalName: String ,
    val overview: String ,
    val popularity: Double ,
    val posterPath: String ,
    val voteAverage: Double ,
    val voteCount: Int,
    val source:String
)