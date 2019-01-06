package com.mmgoogleexpert.ptut.pmovie_tmdb.adapters

import android.content.Context
import android.view.ViewGroup
import com.mmgoogleexpert.ptut.pmovie_tmdb.R
import com.mmgoogleexpert.ptut.pmovie_tmdb.deligate.onTapMovie
import com.mmgoogleexpert.ptut.pmovie_tmdb.network.response.MovieItem
import com.mmgoogleexpert.ptut.pmovie_tmdb.utils.inflate
import com.mmgoogleexpert.ptut.pmovie_tmdb.viewholders.SearchMovieViewHolder
import com.mmgoogleexpert.ptut.shared.model.BaseRecyclerAdapter

class SearchMovieAdapter(context: Context,private val onTapMovie: onTapMovie):
    BaseRecyclerAdapter<SearchMovieViewHolder, MovieItem>(context) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchMovieViewHolder {
        return SearchMovieViewHolder(parent.inflate(R.layout.view_movie_item),onTapMovie)
    }
}
