package com.mmgoogleexpert.ptut.pmovie_tmdb.repository


import com.google.gson.Gson
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.mmgoogleexpert.ptut.pmovie_tmdb.network.MovieApi
import com.mmgoogleexpert.ptut.pmovie_tmdb.utils.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

abstract class BaseRepository protected constructor() {

    protected var mTheApi: MovieApi
//    protected var mTheDB: AppDatabase

    init {
        val logger = HttpLoggingInterceptor()
        logger.level = HttpLoggingInterceptor.Level.BASIC
        val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()

        val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(Gson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build()

        mTheApi = retrofit.create<MovieApi>(MovieApi::class.java)
//        mTheDB = AppDatabase.getDatabase(context)
    }
}