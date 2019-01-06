package com.mmgoogleexpert.ptut.pmovie_tmdb.inject

import android.arch.lifecycle.ViewModelProvider
import com.mmgoogleexpert.ptut.pmovie_tmdb.inject.viewmodelproviders.MovieViewModelProvider
import com.mmgoogleexpert.ptut.pmovie_tmdb.inject.viewmodelproviders.ReviewViewModelProvider
import com.mmgoogleexpert.ptut.pmovie_tmdb.inject.viewmodelproviders.SearchViewModelProvider
import com.mmgoogleexpert.ptut.pmovie_tmdb.mvp.presenters.MoviePresenter
import com.mmgoogleexpert.ptut.pmovie_tmdb.mvp.presenters.ReviewPresenter
import com.mmgoogleexpert.ptut.pmovie_tmdb.mvp.presenters.SearchMoviePresenter

object Injection {
    fun provideViewModelFactoryPopular(categoryPresenter: MoviePresenter): ViewModelProvider.Factory {
        return MovieViewModelProvider(categoryPresenter)
    }

    fun provideViewModelFactorySearch(searchMoviePresenter: SearchMoviePresenter): ViewModelProvider.Factory {
        return SearchViewModelProvider(searchMoviePresenter)
    }

    fun provideViewModelFactoryReview(reviewPresenter: ReviewPresenter): ViewModelProvider.Factory {
        return ReviewViewModelProvider(reviewPresenter)
    }
}