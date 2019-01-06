package com.mmgoogleexpert.ptut.pmovie_tmdb.adapters

import android.support.v7.util.DiffUtil
import android.view.ViewGroup
import com.mmgoogleexpert.ptut.pmovie_tmdb.R
import com.mmgoogleexpert.ptut.pmovie_tmdb.network.response.MovieItem
import com.mmgoogleexpert.ptut.pmovie_tmdb.network.response.ReviewItem
import com.mmgoogleexpert.ptut.pmovie_tmdb.utils.inflate
import com.mmgoogleexpert.ptut.pmovie_tmdb.viewholders.MovieViewHolder
import com.mmgoogleexpert.ptut.pmovie_tmdb.viewholders.ReviewViewHolder
import com.mmgoogleexpert.ptut.shared.model.BaseRecyclerAdapter
import com.mmgoogleexpert.ptut.shared.ui.BaseAdapter

val reviewDiffUtil = object : DiffUtil.ItemCallback<ReviewItem>(){
    override fun areItemsTheSame(oldItem: ReviewItem, newItem: ReviewItem): Boolean {
        return oldItem.id == newItem.id
    }
    override fun areContentsTheSame(oldItem: ReviewItem, newItem: ReviewItem): Boolean {
        return oldItem == newItem
    }
}
class ReviewAdapter:BaseAdapter<ReviewViewHolder,ReviewItem>(reviewDiffUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        return ReviewViewHolder(parent.inflate(R.layout.item_review))
    }
}