package com.oviraptor.oviraptor.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.oviraptor.oviraptor.R
import com.oviraptor.oviraptor.ui.theme.pretendard

@Composable
fun BaseTextField(
    modifier: Modifier = Modifier,
    isPassword: Boolean = false,
    isButton: Boolean = false,
    buttonText:String = "인증",
    onTextChange: (String) -> Unit = {},
    text: String = "adsf",
    placeholder: String = "",
    onClick: () -> Unit = {},
    keyboardType: KeyboardType = KeyboardType.Text,
    buttonColor: Color = Color.Black,
    borderColor: Color = Color(0xFFE1E1E1)
){
    var showPassword by remember { mutableStateOf(false) }
    Box (
        modifier = modifier
            .padding(horizontal = 18.dp)
            .fillMaxWidth()
            .height(50.dp)
            .clip(RoundedCornerShape(5.dp))
            .border(1.dp, color = borderColor, shape = RoundedCornerShape(5.dp)),
    ) {
        BasicTextField(
            singleLine = true,
            modifier = Modifier
                .padding(start = 12.dp)
                .align(Alignment.CenterStart)
                .fillMaxSize(),
            value = text,
            onValueChange = onTextChange,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Go,
                keyboardType = keyboardType
            ),
            decorationBox = { innerTextField ->
                Box(
                    contentAlignment = Alignment.CenterStart
                ) {
                    if (text.isEmpty()) {
                        Text(
                            fontFamily = pretendard,
                            text = placeholder,
                            fontWeight = FontWeight.Medium,
                            fontSize = 16.sp,
                            color = Color.Gray
                        )
                    }
                    innerTextField()
                }
            },
            textStyle = TextStyle(
                fontSize = 16.sp,
                fontFamily = pretendard,
                fontWeight = FontWeight.Medium
            )
        )
        if (isButton){
            Box(
                modifier = Modifier
                    .padding(end = 12.dp)
                    .size(50.dp,30.dp)
                    .align(Alignment.CenterEnd)
                    .clickable { onClick() }
                    .clip(RoundedCornerShape(5.dp))
                    .background(buttonColor),
            ){
                Text(
                    text = buttonText,
                    modifier = Modifier.align(Alignment.Center),
                    color = Color.White,
                    fontSize = 12.sp,
                    fontFamily = pretendard,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
        if (isPassword){
            Image(
                modifier = Modifier
                    .padding(end = 12.dp)
                    .align(Alignment.CenterEnd)
                    .clickable {
                        showPassword = !showPassword
                    },
                painter = painterResource(
                    if (showPassword) {
                        R.drawable.show_password
                    }
                    else {
                        R.drawable.not_show_password
                    }
                ),
                contentDescription = ""
            )
        }
    }
}
@Preview(
    showBackground = true,
)
@Composable
fun BTFPreview(){
    BaseTextField()
}