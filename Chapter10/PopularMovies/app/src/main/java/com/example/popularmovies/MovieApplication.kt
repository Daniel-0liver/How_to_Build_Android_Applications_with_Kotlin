package com.example.popularmovies

import android.app.Application
import com.example.popularmovies.network.MovieService

class MovieApplication : Application() {
    private val apiKey = BuildConfig.TMDB_API_KEY

    private val movieService: MovieService by lazy {
        MovieService(apiKey = apiKey)
    }

    lateinit var movieRepository: MovieRepository

    override fun onCreate() {
        super.onCreate()

        movieRepository = MovieRepository(movieService = movieService)
    }
}