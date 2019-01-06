package com.mmgoogleexpert.ptut.pmovie_tmdb.mvp.presenters

import android.arch.lifecycle.MutableLiveData
import com.mmgoogleexpert.ptut.pmovie_tmdb.deligate.OnTapTrailer
import com.mmgoogleexpert.ptut.pmovie_tmdb.mvp.views.TrailerView
import com.mmgoogleexpert.ptut.pmovie_tmdb.network.response.TrailerItem
import com.mmgoogleexpert.ptut.pmovie_tmdb.repository.TrailerRepository
import com.mmgoogleexpert.ptut.shared.model.BasePresenter

class TrailerPresenter:BasePresenter<TrailerView>(),OnTapTrailer {

    private lateinit var mTrailerListLD:MutableLiveData<List<TrailerItem>>
    override fun initPresenter(mView: TrailerView) {
        super.initPresenter(mView)
        mTrailerListLD= MutableLiveData()
    }

    fun onNotifyCallTrailerVideo(movieId:Int){
        TrailerRepository.getInstance().getAllTrailerList(movieId,mTrailerListLD,errorLD)
    }

    var trailerListLD:MutableLiveData<List<TrailerItem>>?=null
        get() = mTrailerListLD

    override fun onTapTrailer(trailerUrl: String) {
      mView.onLunchTrailer(trailerUrl)
    }
}