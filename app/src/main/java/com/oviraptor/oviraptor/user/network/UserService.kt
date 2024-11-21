package com.oviraptor.oviraptor.user.network

import com.iszero.dgsw_chatting.network.auth.RegisterRequest
import com.iszero.dgsw_chatting.network.auth.LoginRequest
import com.iszero.dgsw_chatting.network.auth.UserResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface UserService {
    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest): UserResponse

    @POST("auth/register")
    suspend fun register(@Body request: RegisterRequest)
}