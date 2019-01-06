package com.mmgoogleexpert.ptut.pmovie_tmdb.activities

import android.annotation.SuppressLint
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import com.bumptech.glide.Glide
import com.mmgoogleexpert.ptut.pmovie_tmdb.R
import com.mmgoogleexpert.ptut.pmovie_tmdb.adapters.ReviewAdapter
import com.mmgoogleexpert.ptut.pmovie_tmdb.adapters.TrailerVideoAdapter
import com.mmgoogleexpert.ptut.pmovie_tmdb.inject.Injection
import com.mmgoogleexpert.ptut.pmovie_tmdb.mvp.presenters.MoviePresenter
import com.mmgoogleexpert.ptut.pmovie_tmdb.mvp.presenters.ReviewPresenter
import com.mmgoogleexpert.ptut.pmovie_tmdb.mvp.presenters.TrailerPresenter
import com.mmgoogleexpert.ptut.pmovie_tmdb.mvp.views.ReviewRecyclerView
import com.mmgoogleexpert.ptut.pmovie_tmdb.mvp.views.TrailerView
import com.mmgoogleexpert.ptut.pmovie_tmdb.network.response.MovieItem
import com.mmgoogleexpert.ptut.pmovie_tmdb.network.response.ReviewItem
import com.mmgoogleexpert.ptut.pmovie_tmdb.network.response.TrailerItem
import com.mmgoogleexpert.ptut.pmovie_tmdb.utils.*
import com.mmgoogleexpert.ptut.shared.ui.BaseActivity
import kotlinx.android.synthetic.main.activity_movie_detail.*
import kotlinx.android.synthetic.main.layout_detail_body.*
import kotlinx.android.synthetic.main.layout_detail_header.*

class MovieDetailActivity:BaseActivity(),ReviewRecyclerView,TrailerView {


    private lateinit var movieItem: MovieItem
    private val reviewPresenter by lazyAndroid {
        ViewModelProviders.of(this,
            Injection.provideViewModelFactoryReview(ReviewPresenter()))
            .get(ReviewPresenter::class.java)
    }
    private val trailerPresenter by lazyAndroid {
        ViewModelProviders.of(this,
            Injection.provideViewModelFactoryTrailer(TrailerPresenter()))
            .get(TrailerPresenter::class.java)
    }
    private val reviewAdapter by lazyAndroid { ReviewAdapter() }
    private val trailerAdapter by lazyAndroid{ TrailerVideoAdapter(trailerPresenter)}

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

        setUpReviewRecycler(movieItem)
        setUpTrailerRecycler(movieItem)
        setUpUiComponentDetail(movieItem)

        movieDetailToolbar.setNavigationOnClickListener {
            onBackPressed()
        }

    }

    private fun setUpReviewRecycler(item:MovieItem){
        //Review Recycler
        reviewPresenter.initPresenter(this)
        reviewPresenter.onNotifyCallReview(item.id!!)
        detailReviewRecycler.setUpRecycler(this)
        detailReviewRecycler.adapter=reviewAdapter
        reviewPresenter.reviewListLD?.observe(this, Observer<List<ReviewItem>>{
            detailReviewRecycler.visibility= View.VISIBLE
            detail_body_reviews.visibility=View.VISIBLE
            reviewAdapter.submitList(it)
        })
    }

    private fun setUpTrailerRecycler(item:MovieItem){
        //Trailer Recycler
        trailerPresenter.initPresenter(this)
        trailerPresenter.onNotifyCallTrailerVideo(item.id!!)
        detailTrailerRecycler.setUpHorizontalRecycler(this)
        detailTrailerRecycler.adapter=trailerAdapter
        trailerPresenter.trailerListLD?.observe(this,Observer<List<TrailerItem>>{
            detailTrailerRecycler.visibility=View.VISIBLE
            detailBodyTrailers.visibility=View.VISIBLE
            trailerAdapter.submitList(it)
        })
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

    override fun onLunchTrailer(trailerUrl: String) {
        val i = Intent(Intent.ACTION_VIEW)
        i.data = Uri.parse(trailerUrl)
        startActivity(i)
    }
}