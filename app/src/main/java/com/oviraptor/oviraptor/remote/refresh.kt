package com.oviraptor.oviraptor.remote

import android.content.Context
import com.oviraptor.oviraptor.user.userinfo.getRefToken
import com.oviraptor.oviraptor.user.userinfo.saveAccToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

suspend fun refresh(context: Context): String? {
    return withContext(Dispatchers.IO) {
        try {
            val refToken = getRefToken(context)
            val userService = Client.userService
            if (refToken != null){
                val response = userService.refresh(refToken)
                saveAccToken(context,response.data)
                "success"
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