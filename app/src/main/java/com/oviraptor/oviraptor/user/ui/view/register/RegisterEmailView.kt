package com.oviraptor.oviraptor.user.ui.view.register

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.oviraptor.oviraptor.ui.component.BackButton
import com.oviraptor.oviraptor.ui.theme.MainColor
import com.oviraptor.oviraptor.ui.theme.pretendard
import com.oviraptor.oviraptor.user.ui.component.AuthTextField
import com.oviraptor.oviraptor.user.ui.component.BaseButton

@Composable
fun RegisterEmailView(
    navController: NavController,
    viewModel: RegisterViewModel
){
    val uiState by viewModel.uiState.collectAsState()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .systemBarsPadding()
    ){
        BackButton(
            modifier = Modifier
                .align(Alignment.TopStart)
                .offset(x = 18.dp, y = 6.dp)
            ,
            navController = navController,
            onClick = {viewModel.updateResult("")}
        )
        Column(
            modifier = Modifier
                .align(Alignment.TopStart)
                .offset(x = 18.dp, y = 40.dp),
        ) {
            Text(
                text = "${uiState.name}님,",
                fontSize = 30.sp,
                fontFamily = pretendard,
                fontWeight = FontWeight.ExtraBold,
                color = MainColor
            )
            Text(
                text = "이메일 주소를 알려 주세요.",
                fontSize = 30.sp,
                fontFamily = pretendard,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black
            )
        }
        Column (
            modifier = Modifier
                .align(Alignment.TopCenter)
                .offset(y = 150.dp),
        ){
            AuthTextField(
                placeholder = "이메일",
                text = uiState.email,
                onTextChange = viewModel::updateEmail,
                isVerify = true,
                keyboardType = KeyboardType.Email
            )
            Spacer(modifier = Modifier.height(15.dp))
            AuthTextField(
                placeholder = "인증코드",
                text = uiState.authCode,
                onTextChange = viewModel::updateAuthCode,
                keyboardType = KeyboardType.Number
            )
        }
        BaseButton(
            modifier = Modifier
                .align(Alignment.BottomCenter),
            text = "완료",
            onClick = {
                viewModel.getAuthCode(uiState.email)
            }
        )
    }
}
@Preview
@Composable
fun RgEmailPv(){
    RegisterEmailView(
        navController = NavController(context = LocalContext.current),
        viewModel = viewModel()
    )
}