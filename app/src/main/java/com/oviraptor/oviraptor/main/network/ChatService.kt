package com.oviraptor.oviraptor.main.network

import com.iszero.dgsw_chatting.network.auth.UserResponse
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ChatService {
    @GET("chat/rooms")
    suspend fun getRooms(
        @Header("Authorization") token: String,
    ): UserResponse

    @POST("chat/rooms")
    suspend fun postRooms(
        @Header("Authorization") token: String,
        @Query("name") name : String
    ): UserResponse

    @POST("user/rooms/{roomId}/invite")
    suspend fun inviteRooms(
        @Header("Authorization") token: String,
        @Path("roomId") roomId: String,
        @Query("participant") participant : String
    )

    @DELETE("chat/rooms/{roomId}")
    suspend fun deleteRooms(
        @Header("Authorization") token: String,
        @Path("roomId") roomId: String,
    )
}