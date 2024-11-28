package com.oviraptor.oviraptor.main.network

import com.oviraptor.oviraptor.main.network.data.GetFriendsResponse
import retrofit2.http.GET
import retrofit2.http.Header

interface FriendService {
    @GET("user/friends")
    suspend fun getFriends(
        @Header("Authorization") token: String,
    ): GetFriendsResponse
}