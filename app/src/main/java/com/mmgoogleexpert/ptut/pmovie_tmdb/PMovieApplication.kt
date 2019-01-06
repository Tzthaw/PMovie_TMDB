package com.mmgoogleexpert.ptut.pmovie_tmdb

import android.annotation.SuppressLint
import android.app.Application
import com.mmgoogleexpert.ptut.pmovie_tmdb.repository.MovieRepository
import com.mmgoogleexpert.ptut.pmovie_tmdb.repository.ReviewRepository
import com.mmgoogleexpert.ptut.pmovie_tmdb.repository.SearchMovieRepository

@SuppressLint("Registered")
class PMovieApplication:Application() {
    companion object {
        lateinit var instance: PMovieApplication
    }
    override fun onCreate() {
        super.onCreate()
        instance=this
        MovieRepository.inintRepository(this)
        SearchMovieRepository.inintRepository(this)
        ReviewRepository.inintRepository(this)
    }
}