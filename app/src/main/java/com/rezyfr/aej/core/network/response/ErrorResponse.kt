package com.rezyfr.aej.core.network.response

data class ErrorResponse(
    val status: String? = null,
    val code: Int? = null,
    val message: String? = null,
    val warning: String? = null
)