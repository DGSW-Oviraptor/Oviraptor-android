package com.oviraptor.oviraptor.main.ui.view.chat

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.google.gson.Gson
import com.oviraptor.oviraptor.main.network.api.getMessages
import com.oviraptor.oviraptor.main.network.api.getRoomDetail
import com.oviraptor.oviraptor.main.network.data.Message
import com.oviraptor.oviraptor.main.network.data.Room
import com.oviraptor.oviraptor.user.userinfo.getAccToken
import com.oviraptor.oviraptor.user.userinfo.getUserName
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch
import ua.naiksoftware.stomp.Stomp
import ua.naiksoftware.stomp.StompClient
import ua.naiksoftware.stomp.dto.StompCommand
import ua.naiksoftware.stomp.dto.StompHeader
import ua.naiksoftware.stomp.dto.StompMessage
data class MessageLite(
    val writer: String,
    val message : String
)

@SuppressLint("CheckResult")
@Composable
fun ChatView(
    navController: NavController,
    roomId: String,
) {
    val listState = rememberLazyListState()
    var room: Room?
    val context = LocalContext.current
    val accToken: String = getAccToken(context)?.replace("Bearer ", "") ?: ""
    val token = StompHeader("Authorization", accToken)
    var text by remember {
        mutableStateOf("")
    }
    val coroutineScope = rememberCoroutineScope()
    var messages by remember {
        mutableStateOf<List<Message>>(emptyList())
    }

    val stompClient: StompClient =
        Stomp.over(Stomp.ConnectionProvider.OKHTTP, "ws://43.203.204.218:8080/ws")
    stompClient.connect()
    LaunchedEffect(Unit) {
        room = getRoomDetail(context, roomId.toInt())
        val response = getMessages(context, roomId.toInt())
        if (response != null) {
            messages = response.reversed()
            if (messages.isNotEmpty()) {
                listState.scrollToItem(messages.size - 1)
            }
        }
        stompClient.topic("/topic/room/${roomId}").subscribe { topicMessage ->
            Log.d(TAG, topicMessage.payload)
            val newMessageLite: MessageLite =
                Gson().fromJson(topicMessage.payload, MessageLite::class.java)
            val userName = getUserName(context) + "asdf"
            Log.d("adf", userName)
            val newMessage = Message(
                id = "hello",
                room = room?.name ?: "",
                writer = newMessageLite.writer,
                content = newMessageLite.message,
                mine = newMessageLite.writer == getUserName(context)
            )
            println(newMessage)
            messages = messages + newMessage
            coroutineScope.launch {
                if (messages.isNotEmpty()) {
                    listState.scrollToItem(messages.size - 1)
                }
            }
        }

    }
    LaunchedEffect(listState) {
        snapshotFlow { listState.firstVisibleItemIndex }
            .distinctUntilChanged()
            .filter { it <= 10 } // 맨 위 아이템으로 스크롤 되었을 때만
            .collect {
                if (messages.isNotEmpty()) {
                    // 기존의 첫 번째 아이템 ID와 인덱스 저장
                    val previousFirstItemIndex = listState.firstVisibleItemIndex
                    val previousFirstItemOffset = listState.firstVisibleItemScrollOffset

                    // API 호출 및 메시지 업데이트
                    val response = getMessages(context, roomId.toInt(), messages[0].id)
                    if (response != null) {
                        messages = response.reversed() + messages

                        // 기존 위치로 스크롤 복원
                        listState.scrollToItem(
                            previousFirstItemIndex + response.size,
                            previousFirstItemOffset
                        )
                    }
                } else {
                    println("messages 리스트가 비어있습니다.")
                }
            }
    }
//    LaunchedEffect(listState) {
//        println("jads")
//        snapshotFlow {
//            val isAtTop = listState.firstVisibleItemIndex == 0 && listState.firstVisibleItemScrollOffset == 0
//            println("isAtTop: $isAtTop")
//            isAtTop
//        }
//            .collect { isAtTop ->
//                if (isAtTop && messages.isNotEmpty()) { // 메시지가 비어 있지 않은지 확인
//                }
//            }
//        }

    DisposableEffect(Unit) {
        onDispose {
            stompClient.disconnect() // WebSocket 연결 해제
        }
    }




    fun sendMessage(text: String) {
        val headers: MutableList<StompHeader> = ArrayList()
        val dest = StompHeader(StompHeader.DESTINATION, "/app/chat/${roomId}")

        headers.add(token)
        headers.add(dest)

        val json = "{ \"message\": \"${text}\" }"

        val message = StompMessage(StompCommand.SEND, headers, json)
        stompClient.send(message).subscribe()
    }
    Box(modifier = Modifier
        .fillMaxSize()
        .systemBarsPadding()) {
        Button(
            modifier = Modifier.align(Alignment.TopStart),
            onClick = {navController.popBackStack()},

        ) {
            Text(text = "메인으로")
        }
        Column(
            modifier = Modifier
                .fillMaxSize(),

            )
        {
            LazyColumn(
                modifier = Modifier.fillMaxSize(0.8f),
                state = listState
            ) {
                items(messages) { item ->
                    MessageItem(item = item)
                }
            }
            Row {
                TextField(
                    modifier = Modifier,
                    value = text,
                    onValueChange = { text = it }
                )

                Button(modifier = Modifier,
                    onClick = {
                        sendMessage(text)
                        text = ""
                    }) {
                }
            }
            Text(text = roomId)
        }
    }
}
@Composable
fun MessageItem(
    item: Message,
){
    Column (
        Modifier
            .fillMaxWidth(),
        horizontalAlignment = if (item.mine){
            Alignment.End
        }
        else {
            Alignment.Start
        }
    ){
        Text(
            modifier = Modifier
                .align(
                    if (item.mine){
                        Alignment.End
                    }
                    else {
                        Alignment.Start
                    }
                ),
            text = item.writer
        )
        Box(modifier = Modifier
            .fillMaxSize(0.5f)
            .align(
                if (item.mine) {
                    Alignment.End
                } else {
                    Alignment.Start
                }
            )
            .background(
                if (item.mine) {
                    Color.Green
                } else {
                    Color.Gray
                }
            )
        ){
            Text(text = item.content)
        }
    }
}