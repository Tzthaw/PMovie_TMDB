package com.mmgoogleexpert.ptut.pmovie_tmdb.repository

import android.content.Context
import com.google.gson.Gson
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.mmgoogleexpert.ptut.pmovie_tmdb.network.MovieApi
import com.mmgoogleexpert.ptut.pmovie_tmdb.utils.BASE_REVIEW_URL
import com.mmgoogleexpert.ptut.pmovie_tmdb.utils.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ReviewRepository private constructor(context: Context): BaseRepository() {

    protected var theApi: MovieApi

    init {
        val logger = HttpLoggingInterceptor()
        logger.level = HttpLoggingInterceptor.Level.BASIC
        val client = OkHttpClient.Builder()
            .addInterceptor(logger)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_REVIEW_URL)
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client)
            .build()

        theApi = retrofit.create<MovieApi>(MovieApi::class.java)
    }

    companion object {
        var INSTANCE: ReviewRepository? = null
        fun getInstance(): ReviewRepository {
            if (INSTANCE == null) {
                throw RuntimeException("ReviewRepository is being invoked before initializing")
            }
            val i = INSTANCE
            return i!!
        }

        fun inintRepository(context: Context) {
            INSTANCE = ReviewRepository(context)
        }
    }

    fun getAllReviewList(

    ){

    }
}