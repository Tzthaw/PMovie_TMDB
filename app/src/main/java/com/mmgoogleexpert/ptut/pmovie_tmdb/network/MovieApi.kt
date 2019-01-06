package com.mmgoogleexpert.ptut.pmovie_tmdb.network

import com.mmgoogleexpert.ptut.pmovie_tmdb.network.response.GetMovieListResponse
import com.mmgoogleexpert.ptut.pmovie_tmdb.network.response.GetReviewResponse
import com.mmgoogleexpert.ptut.pmovie_tmdb.network.response.GetTrailerListResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi{
    @GET("movie/popular")
    fun getPopularMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Observable<GetMovieListResponse>

    @GET("movie/top_rated")
    fun getTopRatedMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Observable<GetMovieListResponse>

    @GET("movie/upcoming")
    fun getUpcomingMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Observable<GetMovieListResponse>

    @GET("movie/{movie_id}/reviews?api_key=db85bc6bf1d96e2f47ac91af80e1d717")
    fun fetchReviews(@Path("movie_id") id: Int): Observable<GetReviewResponse>

    @GET("movie/{movie_id}/videos?api_key=db85bc6bf1d96e2f47ac91af80e1d717")
    fun fetchVideo(@Path("movie_id")id:Int):Observable<GetTrailerListResponse>

    @GET("search/movie")
    fun getSearchMovieList(
        @Query("api_key") apiKey: String,
        @Query("query") movieName: String
    ): Observable<GetMovieListResponse>
}