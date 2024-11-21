package com.oviraptor.oviraptor.user.userinfo

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.runBlocking

val REF_TOKEN = stringPreferencesKey("ref_token")
val ACC_TOKEN = stringPreferencesKey("acc_token")
val USER_NAME = stringPreferencesKey("user_name")

fun saveRefToken(context: Context, token: String) {
    runBlocking {
        context.dataStore.edit { preferences ->
            preferences[REF_TOKEN] = token
        }
    }
}
fun saveAccToken(context: Context, token: String) {
    runBlocking {
        context.dataStore.edit { preferences ->
            preferences[ACC_TOKEN] = token
        }
    }
}
fun saveUserName(context: Context, token: String) {
    runBlocking {
        context.dataStore.edit { preferences ->
            preferences[USER_NAME] = token
        }
    }
}