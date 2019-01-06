package com.mmgoogleexpert.ptut.pmovie_tmdb.network.response

data class GetTrailerListResponse(
	val id: Int? = null,
	val results: List<TrailerItem?>? = null
)
