package com.oviraptor.oviraptor.home.network.data

data class GetRoomDetailResponse (
    val status : Int,
    val state : String,
    val message :String,
    val data : Room
)