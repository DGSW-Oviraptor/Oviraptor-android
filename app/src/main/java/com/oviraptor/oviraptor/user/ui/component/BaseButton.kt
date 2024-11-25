package com.oviraptor.oviraptor.user.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.oviraptor.oviraptor.ui.theme.MainColor
import com.oviraptor.oviraptor.ui.theme.pretendard

@Composable
fun BaseButton(
    modifier: Modifier = Modifier,
    text: String = "버튼",
    onClick: () -> Unit = {},
    color: Color = MainColor,
    textColor : Color = Color.White
){
    Box(
        modifier = modifier
            .padding(horizontal = 18.dp)
            .fillMaxWidth()
            .height(60.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(color)
            .clickable { onClick() },
    ){
        Text(
            modifier = Modifier
                .align(Alignment.Center),
            text = text,
            fontFamily = pretendard,
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            color = textColor
        )
    }
}
@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun BSbtnPreview(
){
    BaseButton()
}