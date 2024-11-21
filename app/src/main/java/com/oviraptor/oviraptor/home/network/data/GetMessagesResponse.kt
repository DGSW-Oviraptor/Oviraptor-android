package com.oviraptor.oviraptor.home.network.data

data class GetMessagesResponse (
    val status : Int,
    val state : String,
    val message :String,
    val data : List<Message>
)