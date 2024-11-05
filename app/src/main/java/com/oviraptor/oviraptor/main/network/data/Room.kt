package com.oviraptor.oviraptor.main.network.data

data class Room(
    val id : Int,
    val name : String,
    val participants: List<String>
)