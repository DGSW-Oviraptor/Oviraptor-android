package com.oviraptor.oviraptor.user.network

import com.oviraptor.oviraptor.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Client {
    private var retrofit: Retrofit? = null


    fun getClient(baseUrl: String): Retrofit {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit!!
    }

    val authService: UserService by lazy {
        val url = BuildConfig.BASE_URL
        getClient(url).create(UserService::class.java)
    }
}