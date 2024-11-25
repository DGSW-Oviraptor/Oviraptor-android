package com.oviraptor.oviraptor.main.network.data

data class Message (
    val id : String,
    val room : String,
    val writer : String,
    val content : String,
    val isMine : Boolean
)