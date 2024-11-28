package com.oviraptor.oviraptor.main.network.data

data class GetFriendsResponse (
    val status : Int,
    val state : String,
    val message :String,
    val data : List<Friend>
)