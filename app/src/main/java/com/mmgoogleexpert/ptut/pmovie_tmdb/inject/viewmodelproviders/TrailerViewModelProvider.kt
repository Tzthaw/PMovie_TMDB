package com.mmgoogleexpert.ptut.pmovie_tmdb.inject.viewmodelproviders

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.mmgoogleexpert.ptut.pmovie_tmdb.mvp.presenters.SearchMoviePresenter
import com.mmgoogleexpert.ptut.pmovie_tmdb.mvp.presenters.TrailerPresenter

class TrailerViewModelProvider(private val repository: TrailerPresenter) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TrailerPresenter::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return repository as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}