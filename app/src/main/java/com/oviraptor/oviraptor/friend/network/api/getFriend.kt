package com.oviraptor.oviraptor.friend.network.api

import android.content.Context
import com.oviraptor.oviraptor.friend.network.data.Friend
import com.oviraptor.oviraptor.remote.Client
import com.oviraptor.oviraptor.user.userinfo.getAccToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

suspend fun getFriend(context: Context): List<Friend>? {
    return withContext(Dispatchers.IO) {
        try {
            val accessToken = getAccToken(context)
            val chatService = Client.friendService
            if (accessToken != null){
                val response = chatService.getFriend(accessToken)
                response
            }
            else{
                null
            }
        }
        catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}