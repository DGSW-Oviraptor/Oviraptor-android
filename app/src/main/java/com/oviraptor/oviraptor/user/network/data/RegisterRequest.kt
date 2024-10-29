package com.iszero.dgsw_chatting.network.auth

data class RegisterRequest (
    val email: String,
    val name: String,
    val password: String
)