package com.oviraptor.oviraptor.home.network.api

import android.content.Context
import com.oviraptor.oviraptor.home.network.data.Message
import com.oviraptor.oviraptor.home.network.data.Room
import com.oviraptor.oviraptor.remote.Client
import com.oviraptor.oviraptor.user.userinfo.getAccToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

suspend fun getMessages(context: Context, roomId : Int): List<Message>? {
    return withContext(Dispatchers.IO) {
        try {
            val accessToken = getAccToken(context)
            val chatService = Client.chatService
            if (accessToken != null){
                val response = chatService.getMessages(accessToken,roomId)
                response.data
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