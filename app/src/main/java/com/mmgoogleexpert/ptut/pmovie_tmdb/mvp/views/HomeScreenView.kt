package com.mmgoogleexpert.ptut.pmovie_tmdb.mvp.views

import android.widget.ImageView
import com.mmgoogleexpert.ptut.pmovie_tmdb.network.response.MovieItem
import com.mmgoogleexpert.ptut.shared.model.BaseView

interface HomeScreenView:BaseView{
    fun onLunchMovieDetail(movieItem:MovieItem,imageView: ImageView)
}