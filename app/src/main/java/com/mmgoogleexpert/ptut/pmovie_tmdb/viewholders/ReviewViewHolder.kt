package com.mmgoogleexpert.ptut.pmovie_tmdb.viewholders

import android.view.View
import com.mmgoogleexpert.ptut.pmovie_tmdb.network.response.ReviewItem
import com.mmgoogleexpert.ptut.shared.ui.BaseViewHolder
import kotlinx.android.synthetic.main.item_review.view.*

class ReviewViewHolder(itemView:View): BaseViewHolder<ReviewItem>(itemView) {
    override fun bindData(review: ReviewItem) {
        super.bindData(review)
        itemView.run {
            reviewTitle.text = review.author
            reviewContent.text = review.content
        }
    }
    override fun onClick(v: View?) {

    }
}