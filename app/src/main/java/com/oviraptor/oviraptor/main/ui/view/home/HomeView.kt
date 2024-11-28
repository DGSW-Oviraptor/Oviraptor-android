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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
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
import com.oviraptor.oviraptor.main.network.data.Friend
import com.oviraptor.oviraptor.main.network.data.Room
import com.oviraptor.oviraptor.nav.NavGroup
import com.oviraptor.oviraptor.ui.component.BaseTextField
import com.oviraptor.oviraptor.ui.theme.MainColor
import com.oviraptor.oviraptor.ui.theme.dropShadow
import com.oviraptor.oviraptor.ui.theme.pretendard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView(navController: NavController, viewModel: HomeViewModel = viewModel()) {
    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )
    LaunchedEffect(Unit) {
        viewModel.getRooms(context)
        viewModel.getFriends(context)
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
                    .align(Alignment.CenterVertically)
                    .clickable { viewModel.updateIsAddRoom(true) },
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
        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .offset(x = (-18).dp, y = (-18).dp)
                .size(53.dp)
                .dropShadow(blur = 6.dp, shape = CircleShape, color = Color.Black.copy(0.1f))
                .clip(CircleShape)
                .background(Color.White)
                .clickable {viewModel.updateIsAddFriend(true)}
        ){
            Image(
                modifier = Modifier
                    .align(Alignment.Center),
                painter = painterResource(R.drawable.add_friend_icon),
                contentDescription = ""
            )
        }
        if (uiState.isAddRoom) {
            ModalBottomSheet(
                modifier = Modifier
                    .height(150.dp)
                    .fillMaxWidth()
                ,
                sheetState = sheetState,
                onDismissRequest = {
                    viewModel.updateIsAddRoom(false)
                },
                containerColor = Color.White,
                shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
            ) {
                Box(Modifier.fillMaxSize()) {
                    Text(
                        "채팅방 만들기",
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .offset(x = 18.dp),
                        fontFamily = pretendard,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MainColor
                    )
                    BaseTextField(
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .offset(y = 30.dp),
                        placeholder = "방 이름",
                        text = uiState.addRoomName,
                        onTextChange = viewModel::updateAddRoomName,
                        isButton = true,
                        buttonColor = Color(0xFF679EFF),
                        borderColor = MainColor,
                        buttonText = "생성",
                        onClick = {viewModel.addRoom(context,uiState.addRoomName)}
                    )
                }
            }
        }
        if (uiState.isAddFriend) {
            ModalBottomSheet(
                modifier = Modifier
                    .height(500.dp)
                    .fillMaxWidth()
                ,
                sheetState = sheetState,
                onDismissRequest = {
                    viewModel.updateIsAddFriend(false)
                },
                containerColor = Color.White,
                shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
            ) {
                Box(Modifier.fillMaxSize()) {
                    Text(
                        "친구 추가하기",
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .offset(x = 18.dp),
                        fontFamily = pretendard,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MainColor
                    )
                    BaseTextField(
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .offset(y = 30.dp),
                        placeholder = "이메일",
                        text = uiState.friendEmail,
                        onTextChange = viewModel::updateFriendEmail,
                        isButton = true,
                        buttonColor = Color(0xFF679EFF),
                        borderColor = MainColor,
                        buttonText = "추가",
                        onClick = {viewModel.addFriend(context,uiState.friendEmail)}
                    )
                    LazyColumn(
                        modifier = Modifier
                            .padding(top = 100.dp)
                            .fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        items(uiState.friends){ friend ->
                            FriendItem(friend)
                        }
                    }
                }
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
@Composable
fun FriendItem(
    friend : Friend,
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
        Column(
            modifier = Modifier.align(Alignment.CenterVertically)
        ){
            Text(
                friend.name,
                fontFamily = pretendard,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black
            )
            Text(
                friend.email,
                fontFamily = pretendard,
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal,
                color = Color(0XFF878787)
            )
        }
        Spacer(modifier = Modifier.weight(1f).fillMaxHeight())
        Row(modifier = Modifier
            .padding(end = 18.dp)
                .height(34.dp)
                .clickable {  }
                .align(Alignment.CenterVertically)
        ){
            Spacer(modifier = Modifier.width(5.dp))
            Image(
                modifier = Modifier.align(Alignment.CenterVertically),
                painter = painterResource(R.drawable.delete_icon),
                contentDescription = ""
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                modifier = Modifier.align(Alignment.CenterVertically),
                text = "친구 삭제",
                fontFamily = pretendard,
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Red
            )
            Spacer(modifier = Modifier.width(5.dp))
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
