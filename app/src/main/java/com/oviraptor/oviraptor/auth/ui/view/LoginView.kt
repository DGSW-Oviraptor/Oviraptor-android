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
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LoginScreen() {
    var emailField by remember { mutableStateOf(TextFieldValue("")) }
    var passwordField by remember { mutableStateOf(TextFieldValue("")) }
    Box(Modifier.fillMaxSize()) {
        Text(
            text = "LOGIN",
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
            value = passwordField,
            onValueChange = { passwordField = it },
            modifier = Modifier
                .align(Alignment.Center)
                .offset(y = 20.dp),
            placeholder = { Text(text = "비밀번호를 입력하세요") }
        )
        Button(
            onClick = {
                TODO("로그인 시행")
            },
            modifier = Modifier
                .align(Alignment.Center)
                .offset(y = 100.dp)
                .size(300.dp, 50.dp)
        )
        {
            Text(text = "로그인")
        }
    }
}
@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun LoginScreenPreview() {
    LoginScreen()
}
