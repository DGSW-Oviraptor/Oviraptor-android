package com.iszero.dgsw_chatting.network.auth

data class LoginResponse (
    val status : Int,
    val state : String,
    val message : String,
    val data : LoginResponseData
)
data class LoginResponseData (
    val accessToken : String,
    val refreshToken : String
)