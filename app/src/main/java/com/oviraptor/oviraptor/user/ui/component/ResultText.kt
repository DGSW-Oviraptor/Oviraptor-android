package com.oviraptor.oviraptor.user.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.oviraptor.oviraptor.R
import com.oviraptor.oviraptor.ui.theme.pretendard

@Composable
fun ResultText(
    modifier: Modifier = Modifier,
    text: String = "안녕하세요"
){
    if (text.isNotEmpty()) {
        Row(
            modifier = modifier
        ) {
            Image(
                modifier = Modifier
                    .align(Alignment.CenterVertically),
                painter = painterResource(R.drawable.error_dot),
                contentDescription = ""
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
                modifier = Modifier
                    .align(Alignment.CenterVertically),
                text = text,
                fontSize = 13.sp,
                fontFamily = pretendard,
                fontWeight = FontWeight.Normal,
                color = Color(0XFFFF757E)
            )
        }
    }
}
@Preview
@Composable
fun ErTxtPrv(){
    ResultText()
}