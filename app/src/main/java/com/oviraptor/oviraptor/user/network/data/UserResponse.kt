package com.iszero.dgsw_chatting.network.auth

data class UserResponse (
    val status : Int,
    val state : String,
    val message : String,
    val data : UserResponseData
)
data class UserResponseData (
    val accessToken : String,
    val refreshToken : String,
    val username : String
)