package com.oviraptor.oviraptor.user.network.api

import android.content.Context
import com.iszero.dgsw_chatting.network.auth.LoginRequest
import com.oviraptor.oviraptor.user.network.Client
import com.oviraptor.oviraptor.user.userinfo.saveAccToken
import com.oviraptor.oviraptor.user.userinfo.saveRefToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

suspend fun login(context: Context, email: String, password: String): String? {
    return withContext(Dispatchers.IO) {
        try {
            val authService = Client.authService
            val request = LoginRequest(email, password)
            val response = authService.login(request)
            saveAccToken(context, response.data.accessToken)
            saveRefToken(context, response.data.refreshToken)
            "success"
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}