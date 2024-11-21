package com.oviraptor.oviraptor.user.network.api

import android.content.Context
import com.oviraptor.oviraptor.remote.Client
import com.oviraptor.oviraptor.user.network.data.UserInfo
import com.oviraptor.oviraptor.user.userinfo.getAccToken
import com.oviraptor.oviraptor.user.userinfo.saveUserName
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

suspend fun getUserInfo(context: Context): UserInfo? {
    return withContext(Dispatchers.IO) {
        try {
            val authService = Client.userService
            val token = getAccToken(context) + ""
            val response = authService.getUserInfo(token)
            saveUserName(context,response.data.name)
            response.data
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}