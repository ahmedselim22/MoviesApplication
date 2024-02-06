package com.selim.moviesapplication.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.selim.moviesapplication.data.local.MovieTvShowDao
import com.selim.moviesapplication.data.local.MovieTvShowDatabase
//import com.selim.moviesapplication.data.local.MovieTvShowDao
//import com.selim.moviesapplication.data.local.MovieTvShowDatabase
import com.selim.moviesapplication.data.remote.ApiService
import com.selim.moviesapplication.repository.MovieRepository
import com.selim.moviesapplication.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MovieModule {

    @Provides
    @Singleton
    fun providesPopularMoviesInterface():ApiService{
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun providesMovieRepository(apiService:ApiService,dao:MovieTvShowDao):MovieRepository{
        return MovieRepository(apiService,dao)
    }

//    @Provides
//    @ApplicationContext
//    fun provideContext(context: Application): Context {
//        return context
//    }

    @Provides
    @Singleton
    fun providesDatabaseDao(database: MovieTvShowDatabase): MovieTvShowDao {
        return database.getDao()
    }

    @Provides
    @Singleton
    fun providesDatabaseInstance(@ApplicationContext context: Context):MovieTvShowDatabase{
        return Room.databaseBuilder(
            context,
            MovieTvShowDatabase::class.java,
            "my_database")
            .fallbackToDestructiveMigration()
            .build()
    }
}