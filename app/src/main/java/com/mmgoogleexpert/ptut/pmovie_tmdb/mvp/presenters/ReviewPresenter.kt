package com.mmgoogleexpert.ptut.pmovie_tmdb.mvp.presenters

import android.arch.lifecycle.MutableLiveData
import com.mmgoogleexpert.ptut.pmovie_tmdb.mvp.views.ReviewRecyclerView
import com.mmgoogleexpert.ptut.pmovie_tmdb.network.response.ReviewItem
import com.mmgoogleexpert.ptut.pmovie_tmdb.repository.ReviewRepository
import com.mmgoogleexpert.ptut.shared.model.BasePresenter

class ReviewPresenter:BasePresenter<ReviewRecyclerView>() {
    private lateinit var mReviewListLD:MutableLiveData<List<ReviewItem>>
    override fun initPresenter(mView: ReviewRecyclerView) {
        super.initPresenter(mView)
        mReviewListLD=MutableLiveData()
    }

    fun onNotifyCallReview(movieId:Int){
        ReviewRepository.getInstance().getAllReviewList(movieId,mReviewListLD,errorLD)
    }

    var reviewListLD:MutableLiveData<List<ReviewItem>>? = null
        get() = mReviewListLD
}