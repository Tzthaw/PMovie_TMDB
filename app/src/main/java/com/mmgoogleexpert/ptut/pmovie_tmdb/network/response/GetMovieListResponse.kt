package com.mmgoogleexpert.ptut.pmovie_tmdb.network.response

data class GetMovieListResponse(
    val page: Int? = null,
    val totalPages: Int? = null,
    val results: List<MovieItem?>? = null,
    val totalResults: Int? = null
)
