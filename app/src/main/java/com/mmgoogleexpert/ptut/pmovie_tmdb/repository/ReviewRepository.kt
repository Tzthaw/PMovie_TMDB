package com.mmgoogleexpert.ptut.pmovie_tmdb.repository

import android.content.Context

class ReviewRepository private constructor(context: Context): BaseRepository() {



    companion object {
        var INSTANCE: ReviewRepository? = null
        fun getInstance(): ReviewRepository {
            if (INSTANCE == null) {
                throw RuntimeException("PopularRepository is being invoked before initializing")
            }
            val i = INSTANCE
            return i!!
        }

        fun inintRepository(context: Context) {
            INSTANCE = ReviewRepository(context)
        }
    }
}