package com.mmgoogleexpert.ptut.pmovie_tmdb.network.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class MovieItem(
	val overview: String? = null,
	@SerializedName("original_language")
	@Expose
	val originalLanguage: String? = null,
	@SerializedName("original_title")
	@Expose
	val originalTitle: String? = null,
	val video: Boolean? = null,
	val title: String? = null,
	@SerializedName("genre_ids")
	@Expose
	val genreIds: List<Int?>? = null,
	@SerializedName("poster_path")
	@Expose
	val posterPath: String? = null,
	@SerializedName("backdrop_path")
	@Expose
	val backdropPath: String? = null,
	@SerializedName("release_date")
	@Expose
	val releaseDate: String? = null,
	@SerializedName("vote_average")
	@Expose
	val voteAverage: Double? = null,
	val popularity: Double? = null,
	val id: Int? = null,
	val adult: Boolean? = null,
	val voteCount: Int? = null
):Serializable
