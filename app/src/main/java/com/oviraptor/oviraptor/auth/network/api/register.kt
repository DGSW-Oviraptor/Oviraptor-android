package com.oviraptor.oviraptor.auth.network.api

import com.iszero.dgsw_chatting.network.auth.RegisterRequest
import com.oviraptor.oviraptor.auth.network.Client
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

suspend fun register(email : String, name : String, password : String): String? {
    return withContext(Dispatchers.IO) {
        try {
            val authService = Client.authService
            val request = RegisterRequest(email, name, password)
            authService.register(request)
            "success"
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}