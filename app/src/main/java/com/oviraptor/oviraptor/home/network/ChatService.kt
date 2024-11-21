package com.oviraptor.oviraptor.home.network

import com.iszero.dgsw_chatting.network.auth.UserResponse
import com.oviraptor.oviraptor.home.network.data.GetRoomsResponse
import com.oviraptor.oviraptor.home.network.data.Room
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
    ): GetRoomsResponse

    @POST("chat/create")
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
    @GET("chat/{roomId}")
    suspend fun getMessage(
        @Header("Authorization") token: String,
        @Path("roomId") roomId: Int,
    )
    @GET("chat/info/{roomId}")
    suspend fun getRoomDetail(
        @Header("Authorization") token: String,
        @Path("roomId") roomId: Int
    )
}