package com.mmgoogleexpert.ptut.pmovie_tmdb.repository

import android.arch.lifecycle.MutableLiveData
import android.content.Context
import com.mmgoogleexpert.ptut.pmovie_tmdb.network.response.GetReviewResponse
import com.mmgoogleexpert.ptut.pmovie_tmdb.network.response.GetTrailerListResponse
import com.mmgoogleexpert.ptut.pmovie_tmdb.network.response.ReviewItem
import com.mmgoogleexpert.ptut.pmovie_tmdb.network.response.TrailerItem
import com.mmgoogleexpert.ptut.pmovie_tmdb.utils.scheduler
import com.mmgoogleexpert.ptut.shared.data.EmptyError
import com.mmgoogleexpert.ptut.shared.data.Error
import com.mmgoogleexpert.ptut.shared.data.NetworkError
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class TrailerRepository private constructor(context: Context): BaseRepository() {
    companion object {
        var INSTANCE: TrailerRepository? = null
        fun getInstance(): TrailerRepository {
            if (INSTANCE == null) {
                throw RuntimeException("TrailerRepository is being invoked before initializing")
            }
            val i = INSTANCE
            return i!!
        }

        fun inintRepository(context: Context) {
            INSTANCE = TrailerRepository(context)
        }
    }

    fun getAllTrailerList(
        movieId:Int,
        trailerListLD:MutableLiveData<List<TrailerItem>>,
        errorLD:MutableLiveData<Error>
    ){
        mTheApi.fetchVideo(movieId)
            .subscribeOn(scheduler)
            .observeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<GetTrailerListResponse> {
                override fun onComplete() {}
                override fun onSubscribe(d: Disposable) {}
                override fun onNext(getTrailerListResponse: GetTrailerListResponse) {
                    if(getTrailerListResponse.results!!.isNotEmpty() ){
                        trailerListLD.value= getTrailerListResponse.results as List<TrailerItem>?
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