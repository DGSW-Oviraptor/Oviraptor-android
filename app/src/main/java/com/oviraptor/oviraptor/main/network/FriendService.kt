package com.oviraptor.oviraptor.main.network

import com.oviraptor.oviraptor.main.network.data.GetFriendsResponse
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface FriendService {
    @GET("user/friends")
    suspend fun getFriends(
        @Header("Authorization") token: String,
    ): GetFriendsResponse

    @POST("user/friends")
    suspend fun addFriend(
        @Header("Authorization") token: String,
        @Query("email") email: String
    )

    @DELETE("user/friends/delete")
    suspend fun deleteFriend(
        @Header("Authorization") token: String,
        @Query("email") email: String
    )
}