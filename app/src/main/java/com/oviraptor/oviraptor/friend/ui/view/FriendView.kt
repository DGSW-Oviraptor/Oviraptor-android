package com.oviraptor.oviraptor.friend.ui.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.oviraptor.oviraptor.friend.network.api.addFriend
import com.oviraptor.oviraptor.friend.network.api.getFriend
import com.oviraptor.oviraptor.friend.network.data.Friend
import com.oviraptor.oviraptor.nav.NavGroup
import kotlinx.coroutines.launch

@Composable
fun FriendView(navController: NavController){
    val context = LocalContext.current
    var friendList by remember { mutableStateOf<List<Friend>>(emptyList()) }
    val coroutineScope = rememberCoroutineScope()
    var friendEmail by remember {mutableStateOf("")}

    LaunchedEffect(Unit) {
        coroutineScope.launch {
            val data = getFriend(context)
            if (data != null){
                friendList = data
            }
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
    ){
        Column {
            Row {
                Button(
                    content = {
                        Text(text = "홈화면 가기")
                    },
                    onClick = { navController.navigate(NavGroup.HOME) }
                )
            }
            ItemList(itemList = friendList)
            TextField(value = friendEmail, onValueChange = {friendEmail = it})
            Button(
                onClick = {
                    coroutineScope.launch {
                        addFriend(context,friendEmail)
                    }
                          },
                content = {
                    Text(text = "친구추가")
                }
            )
        }
    }
}
@Composable
fun ItemList(itemList: List<Friend>) {
    LazyColumn {
        items(itemList) { item ->
            ItemRow(item)
        }
    }
}
@Composable
fun ItemRow(item: Friend) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(text = item.name)
        Spacer(modifier = Modifier.height(4.dp))
    }
}