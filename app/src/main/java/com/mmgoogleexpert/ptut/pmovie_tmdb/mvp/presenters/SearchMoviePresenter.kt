package com.mmgoogleexpert.ptut.pmovie_tmdb.mvp.presenters

import android.arch.lifecycle.MutableLiveData
import android.widget.ImageView
import com.mmgoogleexpert.ptut.pmovie_tmdb.deligate.onTapMovie
import com.mmgoogleexpert.ptut.pmovie_tmdb.mvp.views.HomeScreenView
import com.mmgoogleexpert.ptut.pmovie_tmdb.network.response.MovieItem
import com.mmgoogleexpert.ptut.pmovie_tmdb.repository.SearchMovieRepository
import com.mmgoogleexpert.ptut.shared.model.BasePresenter

class SearchMoviePresenter:BasePresenter<HomeScreenView>(),onTapMovie {
    private lateinit var mSearchMovieListLD: MutableLiveData<List<MovieItem>>
    override fun initPresenter(mView: HomeScreenView) {
        super.initPresenter(mView)
        mSearchMovieListLD= MutableLiveData()
    }

    fun onNotifySearchMovie(movieName:String){
        SearchMovieRepository.getInstance().getSearchMovieList(movieName,mSearchMovieListLD,errorLD)
    }

    var searchMovieListLD:MutableLiveData<List<MovieItem>>? = null
        get() = mSearchMovieListLD

    override fun onTapClick(movieItem: MovieItem,imageView: ImageView) {
        mView.onLunchMovieDetail(movieItem,imageView)
    }
}