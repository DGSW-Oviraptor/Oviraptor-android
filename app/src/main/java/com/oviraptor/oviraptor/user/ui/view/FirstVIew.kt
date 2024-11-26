package com.oviraptor.oviraptor.user.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.oviraptor.oviraptor.nav.NavGroup
import com.oviraptor.oviraptor.ui.theme.MainColor
import com.oviraptor.oviraptor.ui.theme.SubColor
import com.oviraptor.oviraptor.ui.theme.pretendard
import com.oviraptor.oviraptor.user.ui.component.BaseButton

@Composable
fun FirstView(navController : NavController){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .systemBarsPadding()
    ) {
        Column (
            modifier = Modifier
                .align(Alignment.TopCenter)
                .offset(y = 182.dp)
        ){
            Text(
                text = "연결의 시작, 대화의 확장",
                fontFamily = pretendard,
                fontSize = 16.sp,
                color = Color.Black,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = "Oviraptor",
                fontFamily = pretendard,
                fontSize = 64.sp,
                color = MainColor,
                fontWeight = FontWeight.SemiBold
            )
        }
        BaseButton(
            onClick = {navController.navigate(NavGroup.LOGIN)},
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .offset(y = (-70).dp),
            text = "로그인"
        )
        BaseButton(
            onClick = {navController.navigate(NavGroup.REGISTER_NAME)},
            modifier = Modifier
                .align(Alignment.BottomCenter),
            color = SubColor,
            text = "회원가입"
        )
    }
}
@Preview
@Composable
fun FirstViewPreview(){
    FirstView(navController = NavController(context = LocalContext.current))
}

