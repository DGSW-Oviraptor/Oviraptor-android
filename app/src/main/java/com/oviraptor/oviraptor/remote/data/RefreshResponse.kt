package com.oviraptor.oviraptor.remote.data

data class RefreshResponse(
    val status: Int,
    val state: String,
    val message: String,
    val data : String
)