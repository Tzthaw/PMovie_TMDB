package com.mmgoogleexpert.ptut.pmovie_tmdb.repository

import android.arch.lifecycle.MutableLiveData
import android.content.Context
import com.mmgoogleexpert.ptut.pmovie_tmdb.BuildConfig
import com.mmgoogleexpert.ptut.pmovie_tmdb.network.response.GetMovieListResponse
import com.mmgoogleexpert.ptut.pmovie_tmdb.network.response.MovieItem
import com.mmgoogleexpert.ptut.pmovie_tmdb.utils.LANGUAGE
import com.mmgoogleexpert.ptut.pmovie_tmdb.utils.page_id
import com.mmgoogleexpert.ptut.pmovie_tmdb.utils.scheduler
import com.mmgoogleexpert.ptut.shared.data.EmptyError
import com.mmgoogleexpert.ptut.shared.data.Error
import com.mmgoogleexpert.ptut.shared.data.NetworkError
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MovieRepository private constructor(context: Context): BaseRepository() {



    companion object {
        var INSTANCE: MovieRepository? = null
        fun getInstance(): MovieRepository {
            if (INSTANCE == null) {
                throw RuntimeException("PopularRepository is being invoked before initializing")
            }
            val i = INSTANCE
            return i!!
        }

        fun inintRepository(context: Context) {
            INSTANCE = MovieRepository(context)
        }
    }

    fun getPopularMovieList(
        popularMovieListLD:MutableLiveData<List<MovieItem>>,
        errorLD:MutableLiveData<Error>
    ){
        mTheApi.getPopularMovies(BuildConfig.TMDB_API_KEY, LANGUAGE, page_id)
            .subscribeOn(scheduler)
            .observeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<GetMovieListResponse> {
                override fun onComplete() {}
                override fun onSubscribe(d: Disposable) {}
                override fun onNext(getMovieResponse: GetMovieListResponse) {
                    if(getMovieResponse.results!!.isNotEmpty() ){
                        popularMovieListLD.value= getMovieResponse.results as List<MovieItem>?
                    }else{
                        errorLD.value= EmptyError("No Data")
                    }
                }
                override fun onError(e: Throwable) {
                    errorLD.value= NetworkError(e.message!!)
                }
            })
    }

    fun getTopRatedMovieList(
        popularMovieListLD:MutableLiveData<List<MovieItem>>,
        errorLD:MutableLiveData<Error>
    ){
        mTheApi.getTopRatedMovies(BuildConfig.TMDB_API_KEY, LANGUAGE, page_id)
            .subscribeOn(scheduler)
            .observeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<GetMovieListResponse> {
                override fun onComplete() {}
                override fun onSubscribe(d: Disposable) {}
                override fun onNext(getMovieResponse: GetMovieListResponse) {
                    if(getMovieResponse.results!!.isNotEmpty() ){
                        popularMovieListLD.value= getMovieResponse.results as List<MovieItem>?
                    }else{
                        errorLD.value= EmptyError("No Data")
                    }
                }
                override fun onError(e: Throwable) {
                    errorLD.value= NetworkError(e.message!!)
                }
            })
    }

    fun getUpComingMovieList(
        popularMovieListLD:MutableLiveData<List<MovieItem>>,
        errorLD:MutableLiveData<Error>
    ){
        mTheApi.getUpcomingMovies(BuildConfig.TMDB_API_KEY, LANGUAGE, page_id)
            .subscribeOn(scheduler)
            .observeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<GetMovieListResponse> {
                override fun onComplete() {}
                override fun onSubscribe(d: Disposable) {}
                override fun onNext(getMovieResponse: GetMovieListResponse) {
                    if(getMovieResponse.results!!.isNotEmpty() ){
                        popularMovieListLD.value= getMovieResponse.results as List<MovieItem>?
                    }else{
                        errorLD.value= EmptyError("No Data")
                    }
                }
                override fun onError(e: Throwable) {
                    errorLD.value= NetworkError(e.message!!)
                }
            })
    }

}