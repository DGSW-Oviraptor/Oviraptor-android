package com.oviraptor.oviraptor.auth.ui.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun RegisterView(navController : NavController){
    var emailField by remember { mutableStateOf(TextFieldValue("")) }
    var nameField by remember { mutableStateOf(TextFieldValue("")) }
    var passwordField by remember { mutableStateOf(TextFieldValue("")) }
    Box(Modifier.fillMaxSize()) {
        Text(
            text = "JOIN",
            modifier = Modifier
                .align(Alignment.TopCenter)
                .offset(y = 70.dp)
            ,
            fontSize = 100.sp,
        )
        TextField(
            value = emailField,
            onValueChange = { emailField = it },
            modifier = Modifier
                .align(Alignment.Center)
                .offset(y = -60.dp),
            placeholder = { Text(text = "이메일을 입력하세요") }
        )
        TextField(
            value = nameField,
            onValueChange = { nameField = it },
            modifier = Modifier
                .align(Alignment.Center)
                .offset(y = 20.dp),
            placeholder = { Text(text = "이름을 입력하세요") }
        )
        TextField(
            value = passwordField,
            onValueChange = { passwordField = it },
            modifier = Modifier
                .align(Alignment.Center)
                .offset(y = 100.dp),
            placeholder = { Text(text = "비밀번호를 입력하세요") }
        )
        Button(
            onClick = {
                TODO("회원가입 코드 넣기")
            },
            modifier = Modifier
                .align(Alignment.Center)
                .offset(y = 180.dp)
                .size(300.dp, 50.dp)
        )
        {
            Text(text = "회원가입")
        }
    }
}
@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun JoinScreenPreview(){
    RegisterView(navController = NavController(context = LocalContext.current))
}
