package com.mmgoogleexpert.ptut.pmovie_tmdb.repository

import android.arch.lifecycle.MutableLiveData
import android.content.Context
import com.mmgoogleexpert.ptut.pmovie_tmdb.BuildConfig
import com.mmgoogleexpert.ptut.pmovie_tmdb.network.response.GetMovieListResponse
import com.mmgoogleexpert.ptut.pmovie_tmdb.network.response.MovieItem
import com.mmgoogleexpert.ptut.pmovie_tmdb.utils.scheduler
import com.mmgoogleexpert.ptut.shared.data.EmptyError
import com.mmgoogleexpert.ptut.shared.data.Error
import com.mmgoogleexpert.ptut.shared.data.NetworkError
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class SearchMovieRepository private constructor(context: Context): BaseRepository() {
    companion object {
        var INSTANCE: SearchMovieRepository? = null
        fun getInstance(): SearchMovieRepository {
            if (INSTANCE == null) {
                throw RuntimeException("SearchMovieRepository is being invoked before initializing")
            }
            val i = INSTANCE
            return i!!
        }

        fun inintRepository(context: Context) {
            INSTANCE = SearchMovieRepository(context)
        }
    }

    fun getSearchMovieList(
        movieName:String,
        searchMovieListLD: MutableLiveData<List<MovieItem>>,
        errorLD: MutableLiveData<Error>
    ){
        mTheApi.getSearchMovieList(BuildConfig.TMDB_API_KEY, movieName)
            .subscribeOn(scheduler)
            .observeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<GetMovieListResponse> {
                override fun onComplete() {}
                override fun onSubscribe(d: Disposable) {}
                override fun onNext(getMovieResponse: GetMovieListResponse) {
                    if(getMovieResponse.results!!.isNotEmpty() ){
                        searchMovieListLD.value= getMovieResponse.results as List<MovieItem>?
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