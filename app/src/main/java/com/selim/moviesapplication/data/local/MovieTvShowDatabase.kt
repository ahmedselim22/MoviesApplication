package com.selim.moviesapplication.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.selim.moviesapplication.model.entities.MovieEntity
import com.selim.moviesapplication.model.entities.TvShowEntity

@Database(entities = [MovieEntity::class , TvShowEntity::class], version = 1)
abstract class MovieTvShowDatabase:RoomDatabase() {
    abstract fun getDao(): MovieTvShowDao
}