package com.mmgoogleexpert.ptut.pmovie_tmdb.network.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class TrailerItem(
	val site: String? = null,
	val size: Int? = null,
	@SerializedName("iso_3166_1")
	@Expose
	val iso31661: String? = null,
	val name: String? = null,
	val id: String? = null,
	val type: String? = null,
	@SerializedName("iso_639_1")
	@Expose
	val iso6391: String? = null,
	val key: String? = null
)
