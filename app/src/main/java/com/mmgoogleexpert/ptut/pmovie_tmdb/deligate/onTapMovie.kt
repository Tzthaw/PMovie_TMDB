package com.mmgoogleexpert.ptut.pmovie_tmdb.deligate

import android.widget.ImageView
import com.mmgoogleexpert.ptut.pmovie_tmdb.network.response.MovieItem

interface onTapMovie {
    fun onTapClick(movieItem:MovieItem,imageView:ImageView)
}