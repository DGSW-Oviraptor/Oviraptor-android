package com.oviraptor.oviraptor.main.ui.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun FriendView(navController: NavController){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
    ){
        LazyColumn {

        }
    }

}