package com.oviraptor.oviraptor.user.network.data



data class GetUserInfoResponse (
    val status : Int,
    val state : String,
    val message : String,
    val data : UserInfo
)
data class UserInfo (
    val email : String,
    val name : String,
    val role : String
)