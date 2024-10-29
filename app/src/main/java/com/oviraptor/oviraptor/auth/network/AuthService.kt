package com.oviraptor.oviraptor.auth.network

import com.iszero.dgsw_chatting.network.auth.RegisterRequest
import com.iszero.dgsw_chatting.network.auth.LoginRequest
import com.iszero.dgsw_chatting.network.auth.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("user/login")
    suspend fun login(@Body request: LoginRequest): LoginResponse

    @POST("user/register")
    suspend fun register(@Body request: RegisterRequest)
}