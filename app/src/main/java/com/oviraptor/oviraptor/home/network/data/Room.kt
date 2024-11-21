package com.oviraptor.oviraptor.home.network.data

data class GetRoomsResponse (
    val status : Int,
    val state : String,
    val message :String,
    val data : List<Room>
)
data class Room(
    val id : Int,
    val name : String,
    val participants: List<String>
)