package com.oviraptor.oviraptor.user.network

import com.iszero.dgsw_chatting.network.auth.RegisterRequest
import com.iszero.dgsw_chatting.network.auth.LoginRequest
import com.iszero.dgsw_chatting.network.auth.UserResponse
import com.oviraptor.oviraptor.remote.data.RefreshResponse
import com.oviraptor.oviraptor.user.network.data.GetUserInfoResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface UserService {
    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest): UserResponse

    @POST("auth/register")
    suspend fun register(@Body request: RegisterRequest)

    @GET("user/info")
    suspend fun getUserInfo(
        @Header("Authorization") token: String,
    ): GetUserInfoResponse
    @GET("mail/code")
    suspend fun getAuthCode(
        @Query("email") email: String
    )
    @POST("auth/refresh")
    suspend fun refresh(
        @Header("Authorization") token: String,
    ):RefreshResponse
}