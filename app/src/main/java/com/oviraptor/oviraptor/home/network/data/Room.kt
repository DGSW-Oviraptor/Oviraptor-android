package com.oviraptor.oviraptor.home.network.data

data class Room(
    val id : Int,
    val name : String,
    val participants: List<String>
)