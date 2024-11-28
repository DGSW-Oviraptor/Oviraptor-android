package com.oviraptor.oviraptor.main.ui.view.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import com.oviraptor.oviraptor.main.network.data.Room
import com.oviraptor.oviraptor.nav.NavGroup
import com.oviraptor.oviraptor.ui.theme.MainColor
import com.oviraptor.oviraptor.ui.theme.pretendard

@Composable
fun HomeView(navController: NavController, viewModel: HomeViewModel = viewModel()) {
    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current
    LaunchedEffect(Unit) {
        viewModel.getRooms(context)
    }
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
        LazyColumn (
            modifier = Modifier
                .padding(top = 73.dp)
                .fillMaxHeight(),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ){
            items(uiState.rooms){ room->
                RoomItem(
                    room,
                    onClick = {
                        navController.navigate("${NavGroup.CHAT}/${room.id}")
                    }
                )
            }
        }
    }
}
@Composable
fun RoomItem(
    room : Room,
    onClick: () -> Unit = {},
){
    Row(
        Modifier
            .padding(horizontal = 18.dp)
            .fillMaxWidth()
            .height(75.dp)
            .clip(RoundedCornerShape(5.dp))
            .background(Color(0xFFF9F9F9))
            .border(width = 1.dp, color = Color(0xFFE0E0E0), shape = RoundedCornerShape(5.dp))
            .clickable { onClick() }
    ){
        Spacer(Modifier.width(17.dp))
        Image(
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .size(41.dp),
            painter = painterResource(R.drawable.chat_image),
            contentDescription = ""
        )
        Spacer(Modifier.width(15.dp))
        Column(
            modifier = Modifier.align(Alignment.CenterVertically)
        ){
            Text(
                room.name,
                fontFamily = pretendard,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black
            )
            Text(
                "${room.participants.size}명 참가중",
                fontFamily = pretendard,
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal,
                color = Color(0XFF878787)
            )
        }
    }
}

@Preview(
    showBackground = true
)
@Composable
fun HomeViewPRV(){
//    HomeView(navController = NavController(context = LocalContext.current))
    RoomItem(room = Room(0,"테스트", listOf("ㅁㄴㅇㄹ","ㄹㅁㅇ")))
}