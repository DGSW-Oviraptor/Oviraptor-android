package com.oviraptor.oviraptor.home.ui.view

import android.R.attr.type
import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.google.gson.Gson
import com.oviraptor.oviraptor.home.network.data.Message
import com.oviraptor.oviraptor.user.userinfo.getAccToken
import ua.naiksoftware.stomp.Stomp
import ua.naiksoftware.stomp.StompClient
import ua.naiksoftware.stomp.dto.StompCommand
import ua.naiksoftware.stomp.dto.StompHeader
import ua.naiksoftware.stomp.dto.StompMessage


@SuppressLint("CheckResult")
@Composable
fun ChatView(
    navController: NavController,
    roomId: String,
){
    val context = LocalContext.current
    val accToken: String = getAccToken(context)?.replace("Bearer ","") ?: ""
    val headers: MutableList<StompHeader> = ArrayList()
    val token: StompHeader = StompHeader("Authorization",accToken)
    var text by remember {
        mutableStateOf("")
    }




    val stompClient: StompClient = Stomp.over(Stomp.ConnectionProvider.OKHTTP, "ws://43.203.204.218:8080/ws")
    stompClient.connect()

    stompClient.topic("/topic/room/${roomId}").subscribe { topicMessage ->
        Log.d(TAG, topicMessage.getPayload())
        val message: Message = Gson().fromJson(topicMessage.getPayload(), Message::class.java)
        println(message.message)
    }


    fun sendMessage(text :String){
        val dest = StompHeader(StompHeader.DESTINATION,"/app/chat/${roomId}")

        headers.add(token)
        headers.add(dest)

        val json = "{ \"message\": \"${text}\" }"

        val message = StompMessage(StompCommand.SEND,headers,json)
        stompClient.send(message).subscribe()
    }
    LaunchedEffect(Unit) {

    }
    Box(modifier = Modifier
        .fillMaxSize()
        .systemBarsPadding())
    {
        TextField(
            modifier = Modifier.align(Alignment.BottomCenter),
            value = text,
            onValueChange = {text = it}
        )

        Button(modifier = Modifier.align(Alignment.TopStart),
            onClick = {
            sendMessage(text)
            text = ""
        }) {

        }

        Text(text = roomId)
    }
    
}