package com.mmgoogleexpert.ptut.pmovie_tmdb.mvp.views

import com.mmgoogleexpert.ptut.shared.model.BaseView

interface TrailerView:BaseView {
    fun onLunchTrailer(trailerUrl:String)
}