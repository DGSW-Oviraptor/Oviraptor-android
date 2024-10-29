package com.oviraptor.oviraptor.auth.ui.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun FirstView(navController : NavController){
    Box(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "First",
            modifier = Modifier
                .align(Alignment.TopCenter)
                .offset(y = 70.dp)
            ,
            fontSize = 100.sp,
        )
        Button(
            onClick = {TODO("로그인 화면 이동")},
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .offset(y = (-120).dp)
                .size(300.dp, 60.dp)
        ) {
            Text(text = "로그인")
        }
        Button(
            onClick = {TODO("회원가입 화면 이동")},
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .offset(y = (-40).dp)
                .size(300.dp, 60.dp)
        ) {
            Text(text = "회원가입")
        }
    }
}
@Preview
@Composable
fun FirstViewPreview(){
    FirstView(navController = NavController(context = LocalContext.current))
}

