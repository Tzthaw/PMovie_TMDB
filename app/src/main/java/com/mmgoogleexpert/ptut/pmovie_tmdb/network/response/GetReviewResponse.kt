package com.mmgoogleexpert.ptut.pmovie_tmdb.network.response

data class GetReviewResponse(
    val id: Int? = null,
    val page: Int? = null,
    val totalPages: Int? = null,
    val results: List<ReviewItem?>? = null,
    val totalResults: Int? = null
)
