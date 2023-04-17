package com.rezyfr.aej.data.model.response

import com.google.gson.annotations.SerializedName

data class MovieListResponse(
    @field:SerializedName("results")
    val results: List<MovieResponse>? = null,
)
