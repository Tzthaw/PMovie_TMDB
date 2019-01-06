package com.mmgoogleexpert.ptut.pmovie_tmdb.mvp.presenters

import android.arch.lifecycle.MutableLiveData
import com.mmgoogleexpert.ptut.pmovie_tmdb.deligate.onTapMovie
import com.mmgoogleexpert.ptut.pmovie_tmdb.mvp.views.HomeScreenView
import com.mmgoogleexpert.ptut.pmovie_tmdb.network.response.MovieItem
import com.mmgoogleexpert.ptut.pmovie_tmdb.repository.MovieRepository
import com.mmgoogleexpert.ptut.shared.model.BasePresenter

class MoviePresenter:BasePresenter<HomeScreenView>(),onTapMovie {
    private lateinit var mPopularMovieListLD:MutableLiveData<List<MovieItem>>
    override fun initPresenter(mView: HomeScreenView) {
        super.initPresenter(mView)
        mPopularMovieListLD= MutableLiveData()
    }

    fun onNotifyCallPopularMovieList(){
        MovieRepository.getInstance().getPopularMovieList(
            mPopularMovieListLD,
            errorLD
        )
    }
    fun onNotifyCallTopRatedMovieList(){
        MovieRepository.getInstance().getTopRatedMovieList(
            mPopularMovieListLD,
            errorLD
        )
    }
    fun onNotifyCallUpComingMovieList(){
        MovieRepository.getInstance().getUpComingMovieList(
            mPopularMovieListLD,
            errorLD
        )
    }

    var popularMovieListLD:MutableLiveData<List<MovieItem>>? = null
        get() = mPopularMovieListLD

    override fun onTapClick(movieItem: MovieItem) {
        mView.onLunchMovieDetail(movieItem)
    }
}