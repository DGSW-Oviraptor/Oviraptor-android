package com.oviraptor.oviraptor.home.network.data

data class Message (
    val id : String,
    val room : String,
    val writer : String,
    val content : String,
    val isMine : Boolean
)