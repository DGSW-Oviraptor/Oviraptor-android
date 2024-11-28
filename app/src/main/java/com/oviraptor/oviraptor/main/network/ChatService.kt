package com.oviraptor.oviraptor.main.network

import com.iszero.dgsw_chatting.network.auth.UserResponse
import com.oviraptor.oviraptor.main.network.data.GetMessagesResponse
import com.oviraptor.oviraptor.main.network.data.GetRoomDetailResponse
import com.oviraptor.oviraptor.main.network.data.GetRoomsResponse
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ChatService {
    @GET("user/rooms")
    suspend fun getRooms(
        @Header("Authorization") token: String,
    ): GetRoomsResponse

    @POST("chat/create")
    suspend fun addRooms(
        @Header("Authorization") token: String,
        @Query("name") name : String
    )

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
    suspend fun getMessages(
        @Header("Authorization") token: String,
        @Path("roomId") roomId: Int,
        @Query("objectId") objectId: String?
    ): GetMessagesResponse
    @GET("chat/info/{roomId}")
    suspend fun getRoomDetail(
        @Header("Authorization") token: String,
        @Path("roomId") roomId: Int
    ): GetRoomDetailResponse
}