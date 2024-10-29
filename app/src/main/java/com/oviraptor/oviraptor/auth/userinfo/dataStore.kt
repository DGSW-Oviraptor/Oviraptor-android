package com.oviraptor.oviraptor.auth.userinfo

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore

val Context.dataStore by preferencesDataStore(name = "user_info")