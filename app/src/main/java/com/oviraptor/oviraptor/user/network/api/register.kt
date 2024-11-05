package com.oviraptor.oviraptor.user.network.api

import com.iszero.dgsw_chatting.network.auth.RegisterRequest
import com.oviraptor.oviraptor.user.network.Client
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

suspend fun register(email : String, name : String, password : String): String? {
    return withContext(Dispatchers.IO) {
        try {
            val authService = Client.userService
            val request = RegisterRequest(email, name, password)
            authService.register(request)
            "success"
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}