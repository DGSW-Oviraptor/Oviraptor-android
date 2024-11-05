package com.oviraptor.oviraptor.main.ui.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
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
import com.oviraptor.oviraptor.main.network.api.getRooms
import com.oviraptor.oviraptor.main.network.data.Room
import kotlinx.coroutines.launch

@Composable
fun HomeView(navController: NavController) {
    val context = LocalContext.current
    var itemList by remember { mutableStateOf<List<Room>>(emptyList()) }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        coroutineScope.launch {
            val data = getRooms(context)
            if (data != null){
                itemList = data
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding(),
    ) {
        ItemList(itemList = itemList)
    }
}

@Composable
fun ItemList(itemList: List<Room>) {
    LazyColumn {
        items(itemList) { item ->
            ItemRow(item)
        }
    }
}
@Composable
fun ItemRow(item: Room) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(text = item.name)
        Spacer(modifier = Modifier.height(4.dp))
    }
}