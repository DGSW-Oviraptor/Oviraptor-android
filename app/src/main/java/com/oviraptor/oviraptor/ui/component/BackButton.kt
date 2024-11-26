package com.oviraptor.oviraptor.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.oviraptor.oviraptor.R

@Composable
fun BackButton(
    modifier: Modifier,
    navController: NavController,
    onClick: () -> Unit = {}
){
    Box(
        modifier = modifier
            .clickable {
                onClick()
                navController.popBackStack()
            }
    ){
        Image(
            painter = painterResource(R.drawable.back_button),
            contentDescription = "",
        )
    }
}