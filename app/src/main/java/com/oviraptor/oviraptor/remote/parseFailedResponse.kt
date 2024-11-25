package com.oviraptor.oviraptor.remote

import com.google.gson.Gson
import com.oviraptor.oviraptor.remote.data.FailedResponse

fun parseFailedResponse(errorBody: String): FailedResponse {
    return try {
        Gson().fromJson(errorBody, FailedResponse::class.java)
    } catch (e: Exception) {
        e.printStackTrace()
        FailedResponse("파싱 오류", "파싱 오류", "알 수 없는 에러가 발생했습니다.")
    }
}