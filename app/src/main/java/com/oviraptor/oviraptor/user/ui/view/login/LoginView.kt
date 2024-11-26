package com.oviraptor.oviraptor.user.ui.view.login

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.oviraptor.oviraptor.nav.NavGroup
import com.oviraptor.oviraptor.ui.component.BackButton
import com.oviraptor.oviraptor.ui.theme.pretendard
import com.oviraptor.oviraptor.user.ui.component.AuthTextField
import com.oviraptor.oviraptor.user.ui.component.BaseButton

@Composable
fun LoginView(
    navController : NavController,
    viewModel: LoginViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(viewModel) {
        viewModel.uiEffect.collect { effect ->
            when (effect) {
                LoginSideEffect.Success -> {
                    viewModel.saveTokens(context)
                    navController.navigate(NavGroup.HOME)
                }
                LoginSideEffect.Failed -> {
                    Log.d("result", "failed")
                }
            }
        }
    }

    Box(
        Modifier
            .fillMaxSize()
            .background(Color.White)
            .systemBarsPadding()
    ) {
        BackButton(
            modifier = Modifier
                .align(Alignment.TopStart)
                .offset(x = 18.dp, y = 6.dp)
            ,
            navController = navController
        )
        Text(
            modifier = Modifier
                .align(Alignment.TopStart)
                .offset(x = 18.dp, y = 42.dp),
            text = "로그인",
            fontSize = 30.sp,
            fontFamily = pretendard,
            fontWeight = FontWeight.SemiBold
        )
        AuthTextField(
            text = uiState.email,
            onTextChange = viewModel::updateEmail,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .offset(y = 120.dp),
            placeholder = "이메일"
        )
        AuthTextField(
            text = uiState.password,
            onTextChange = viewModel::updatePassword,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .offset(y = 190.dp),
            placeholder = "비밀번호",
            isPassword = true
        )
        Text(
            modifier = Modifier.align(Alignment.TopCenter)
                .offset(y = 250.dp),
            text = uiState.error
        )
        BaseButton(
            onClick = {
                viewModel.login(email = uiState.email, password = uiState.password)
            },
            modifier = Modifier
                .align(Alignment.BottomCenter),
            text = "로그인"
        )
    }
}
@Preview(
    showBackground = true,
)
@Composable
fun LoginScreenPreview() {
    LoginView(navController = NavController(context = LocalContext.current))
}
