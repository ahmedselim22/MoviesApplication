package com.selim.moviesapplication.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.selim.moviesapplication.model.entities.MovieEntity
import com.selim.moviesapplication.model.entities.TvShowEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieTvShowDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies:List<MovieEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTvShows(movies:List<TvShowEntity>)

    @Query("SELECT * FROM Movies WHERE source =:source ")
    fun getMovies(source:String): Flow<List<MovieEntity>>

    @Query("SELECT * FROM TvShows WHERE source =:source ")
    fun getTvShows(source:String): Flow<List<TvShowEntity>>

}