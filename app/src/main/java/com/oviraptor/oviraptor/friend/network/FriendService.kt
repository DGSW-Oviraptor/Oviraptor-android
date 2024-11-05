package com.oviraptor.oviraptor.friend.network

import com.oviraptor.oviraptor.friend.network.data.Friend
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface FriendService {
    @GET("user/friends")
    suspend fun getFriend(
        @Header("Authorization") token: String,
    ): List<Friend>
    @POST("user/friend/add")
    suspend fun addFriend(
        @Header("Authorization") token: String,
        @Query("userEmail") userEmail: String
    )
}