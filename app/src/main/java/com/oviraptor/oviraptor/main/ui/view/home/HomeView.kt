package com.oviraptor.oviraptor.main.ui.view.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.oviraptor.oviraptor.R
import com.oviraptor.oviraptor.ui.theme.MainColor
import com.oviraptor.oviraptor.ui.theme.pretendard

@Composable
fun HomeView(navController: NavController, viewModel: HomeViewModel = viewModel()) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .systemBarsPadding()
    ){
        Row (
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 18.dp)
                .offset(y = 21.dp)
        ){
            Text(
                modifier = Modifier
                    .align(Alignment.CenterVertically),
                text = "채팅",
                fontFamily = pretendard,
                fontSize = 30.sp,
                fontWeight = FontWeight.SemiBold,
                color = MainColor
            )
            Spacer(modifier = Modifier.weight(1f))
            Image(
                modifier = Modifier
                    .align(Alignment.CenterVertically),
                painter = painterResource(R.drawable.add_chat_icon),
                contentDescription = "",
            )
        }
    }
}

@Preview(
    showBackground = true
)
@Composable
fun HomeViewPRV(){
    HomeView(navController = NavController(context = LocalContext.current))
}
