package com.mmgoogleexpert.ptut.pmovie_tmdb.activities

import android.annotation.SuppressLint
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import com.bumptech.glide.Glide
import com.mmgoogleexpert.ptut.pmovie_tmdb.R
import com.mmgoogleexpert.ptut.pmovie_tmdb.adapters.ReviewAdapter
import com.mmgoogleexpert.ptut.pmovie_tmdb.inject.Injection
import com.mmgoogleexpert.ptut.pmovie_tmdb.mvp.presenters.MoviePresenter
import com.mmgoogleexpert.ptut.pmovie_tmdb.mvp.presenters.ReviewPresenter
import com.mmgoogleexpert.ptut.pmovie_tmdb.mvp.views.ReviewRecyclerView
import com.mmgoogleexpert.ptut.pmovie_tmdb.network.response.MovieItem
import com.mmgoogleexpert.ptut.pmovie_tmdb.network.response.ReviewItem
import com.mmgoogleexpert.ptut.pmovie_tmdb.utils.BASE_IMG_URL
import com.mmgoogleexpert.ptut.pmovie_tmdb.utils.lazyAndroid
import com.mmgoogleexpert.ptut.pmovie_tmdb.utils.requestGlideListener
import com.mmgoogleexpert.ptut.pmovie_tmdb.utils.setUpRecycler
import com.mmgoogleexpert.ptut.shared.ui.BaseActivity
import kotlinx.android.synthetic.main.activity_movie_detail.*
import kotlinx.android.synthetic.main.layout_detail_body.*
import kotlinx.android.synthetic.main.layout_detail_header.*

class MovieDetailActivity:BaseActivity(),ReviewRecyclerView {
    private lateinit var movieItem: MovieItem
    private val reviewPresenter by lazyAndroid {
        ViewModelProviders.of(this,
            Injection.provideViewModelFactoryReview(ReviewPresenter()))
            .get(ReviewPresenter::class.java)
    }
    private val reviewAdapter by lazyAndroid { ReviewAdapter() }

    companion object {
        fun newIntent(context: Context,movieItem: MovieItem):Intent{
            val intent = Intent(context, MovieDetailActivity::class.java)
            val bundle = Bundle()
            bundle.putSerializable("DetailItem", movieItem)
            intent.putExtra("DetailBundle", bundle)
            return intent
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        movieItem = (intent.getBundleExtra("DetailBundle").getSerializable("DetailItem") as MovieItem?)!!
        setSupportActionBar(movieDetailToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        reviewPresenter.initPresenter(this)
        reviewPresenter.onNotifyCallReview(movieItem.id!!)
        detailReviewRecycler.setUpRecycler(this)
        detailReviewRecycler.adapter=reviewAdapter
        reviewPresenter.reviewListLD?.observe(this, Observer<List<ReviewItem>>{
            detailReviewRecycler.visibility= View.VISIBLE
            detail_body_reviews.visibility=View.VISIBLE
            reviewAdapter.submitList(it)
        })

        movieDetailToolbar.setNavigationOnClickListener {
            onBackPressed()
        }
        setUpUiComponentDetail(movieItem)
    }

    @SuppressLint("SetTextI18n")
    private fun setUpUiComponentDetail(item:MovieItem){
        supportActionBar?.title=item.originalTitle
        detailHeaderTitle.text=item.title
        detailHeaderReleaseDate.text="Release Date : ${item.releaseDate}"
        detailHeaderStar.rating= (item.voteAverage!!.div(2)).toFloat()
        detailLanguage.text=item.originalLanguage
        detailOverview.text=item.overview
        detailVoteAverage.text="Vote Average : ${item.voteAverage}"
        item.backdropPath.let {
            Glide.with(this).load("$BASE_IMG_URL$it")
                .listener(requestGlideListener(movieImage))
                .into(movieImage)
        }
    }
}