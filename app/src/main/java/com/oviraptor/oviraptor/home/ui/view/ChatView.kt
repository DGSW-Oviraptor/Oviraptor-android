package com.oviraptor.oviraptor.home.ui.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun ChatView(
    navController: NavController,
    roomId: String
){
    Box(modifier = Modifier
        .fillMaxSize()
        .systemBarsPadding()){
        Text(text = roomId)
    }
    
}