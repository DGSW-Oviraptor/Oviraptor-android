package com.oviraptor.oviraptor.remote.data

data class FailedResponse(
    val status: Int,
    val state: String,
    val message: String
)