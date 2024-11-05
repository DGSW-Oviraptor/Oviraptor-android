package com.oviraptor.oviraptor.main.network.api

import android.content.Context
import com.oviraptor.oviraptor.remote.Client
import com.oviraptor.oviraptor.main.network.data.Room
import com.oviraptor.oviraptor.user.userinfo.getAccToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

suspend fun getRooms(context: Context): List<Room>? {
    return withContext(Dispatchers.IO) {
        try {
            val accessToken = getAccToken(context)
            val chatService = Client.chatService
            if (accessToken != null){
            val response = chatService.getRooms(accessToken)
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