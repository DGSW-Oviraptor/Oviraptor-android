package com.oviraptor.oviraptor.remote

import com.oviraptor.oviraptor.BuildConfig
import com.oviraptor.oviraptor.main.network.ChatService
import com.oviraptor.oviraptor.main.network.FriendService
import com.oviraptor.oviraptor.user.network.UserService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object Client {
    private var retrofit: Retrofit? = null
    fun getClient(): Retrofit {
        if (retrofit == null) {
            val url = BuildConfig.BASE_URL
            retrofit = Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit!!
    }
    val chatService: ChatService by lazy {
        getClient().create(ChatService::class.java)
    }
    val userService: UserService by lazy {
        getClient().create(UserService::class.java)
    }
    val friendService: FriendService by lazy {
        getClient().create(FriendService::class.java)
    }
}