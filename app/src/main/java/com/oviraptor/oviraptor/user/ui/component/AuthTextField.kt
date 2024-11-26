package com.oviraptor.oviraptor.user.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.oviraptor.oviraptor.R
import com.oviraptor.oviraptor.ui.theme.pretendard

@Composable
fun AuthTextField(
    modifier: Modifier = Modifier,
    isPassword: Boolean = false,
    isVerify: Boolean = false,
    onTextChange: (String) -> Unit = {},
    text: String = "adsf",
    placeholder: String = "",
    onClick: () -> Unit = {},
    keyboardType: KeyboardType = KeyboardType.Text
){
    var showPassword by remember { mutableStateOf(false) }
    var hidePasswordIcon by remember { mutableIntStateOf(value = R.drawable.show_password) }
    Box(
        modifier = modifier
            .padding(horizontal = 18.dp)
            .fillMaxWidth()
            .height(50.dp)
            .clip(RoundedCornerShape(5.dp))
            .background(Color(0xFFFAFAFA))
            .border(
                width = 1.dp,
                color = Color(0xFFE1E1E1),
                shape = RoundedCornerShape(5.dp)
            )
    ) {
        BasicTextField(
            value = text,
            onValueChange = onTextChange,
            modifier = Modifier
                .align(Alignment.CenterStart)
                .height(250.dp)
                .offset(x = 14.dp),
            textStyle = TextStyle(
                fontSize = 16.sp,
                fontFamily = pretendard,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            ),
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType
            ),
            decorationBox = { innerTextField ->
                Box(
                    contentAlignment = Alignment.CenterStart
                ) {
                    if (text.isEmpty()) {
                        Text(
                            text = placeholder,
                            fontFamily = pretendard,
                            fontWeight = FontWeight.Medium,
                            fontSize = 14.sp,
                            color = Color(0xFF7D7D7D)
                        )
                    }
                    innerTextField()
                }
            },
            visualTransformation =
                if (isPassword && !showPassword) {
                    PasswordVisualTransformation()
                } else {
                    VisualTransformation.None
                }
        )
        if (isPassword) {
            Spacer(modifier = Modifier.width(8.dp))
            Box(
                modifier = Modifier
                    .size(20.dp)
                    .align(Alignment.CenterEnd)
                    .offset(x = (-12).dp),
            ) {
                Image(
                    modifier = Modifier
                        .size(20.dp)
                        .clickable {
                            showPassword = !showPassword
                            hidePasswordIcon = R.drawable.not_show_password
                        },
                    painter = painterResource(id = if (showPassword) R.drawable.not_show_password else R.drawable.show_password),
                    contentDescription = "Toggle password visibility",
                )
            }
        }
        if (isVerify){
            Box(
                modifier = Modifier
                    .offset(x = (-13).dp)
                    .size(50.dp,30.dp)
                    .clip(RoundedCornerShape(5.dp))
                    .align(Alignment.CenterEnd)
                    .background(Color.Black)
                    .clickable { onClick() }
            ){
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = "인증",
                    fontFamily = pretendard,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White
                )
            }
        }
    }
}
@Preview(
    showBackground = true,
)
@Composable
fun ATFPreview(){
    AuthTextField()
}