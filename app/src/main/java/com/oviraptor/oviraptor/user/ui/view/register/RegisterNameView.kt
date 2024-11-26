package com.oviraptor.oviraptor.user.ui.view.register

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
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
import com.oviraptor.oviraptor.user.ui.component.ErrorText

@Composable
fun RegisterNameView(
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
            navController = navController
        )
        Text(
            modifier = Modifier
                .align(Alignment.TopStart)
                .offset(x = 18.dp, y = 40.dp),
            text = "이름을 알려 주세요",
            fontSize = 30.sp,
            fontFamily = pretendard,
            fontWeight = FontWeight.SemiBold,
        )
        AuthTextField(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .offset(y = 120.dp),
            placeholder = "이름",
            text = uiState.name,
            onTextChange = viewModel::updateName
        )
        ErrorText(
            modifier = Modifier
                .align(Alignment.TopStart)
                .offset(x = 18.dp, y = 180.dp),
            text = uiState.error
        )
        BaseButton(
            text = "다음",
            modifier = Modifier.align(Alignment.BottomCenter),
            onClick = {
                if (uiState.name.isNotEmpty()) {
                    viewModel.updateError("")
                    navController.navigate(NavGroup.REGISTER_PASSWORD)
                }
                else {
                    viewModel.updateError("이름을 입력해주세요.")
                }
            }
        )
    }
}
@Preview(
    showBackground = true
)
@Composable
fun RNamePreview(){
    RegisterNameView(
        navController = NavController(context = LocalContext.current),
        viewModel = viewModel()
    )
}

