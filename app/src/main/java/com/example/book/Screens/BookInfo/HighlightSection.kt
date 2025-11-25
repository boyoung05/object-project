package com.example.book.Screens.BookInfo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
// 책의 하이라이트 정보는 API로 받을 수 없는 정보일 것 같아서
// 고정 UI로 하는 수 밖에 없을 것 같음
fun HighlightSection() {
    Column {
        Text(
            text = "하이라이트",
            fontSize = 13.sp,
            color = Color(0xFF666666)
        )
        Spacer(modifier = Modifier.height(8.dp))

        //
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(28.dp)
                    .background(Color(0xFFEDEDED), CircleShape)
            )
            Spacer(modifier = Modifier.height(8.dp))

            Column {
                Text(
                    text = "밑줄: X | 필기: O | 찢김: X",
                    fontSize = 12.sp,
                    color = Color(0xFF555555)
                )
            }
        }
    }

}