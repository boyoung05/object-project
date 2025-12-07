package com.example.book.Screens.BookInfo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
// 책의 하이라이트 정보는 API로 받을 수 없는 정보일 것 같아서
// 프론트 내 로직으로 처리
fun HighlightSection() {
    Column {
        Text(
            text = "하이라이트",
            fontSize = 13.sp,
            color = Color(0xFF666666)
        )
        Spacer(modifier = Modifier.height(8.dp))


        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            // 동그라미 + 연필 아이콘
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(Color(0xFFEDEDED), CircleShape),
                contentAlignment = Alignment.Center // 안의 내용 중앙 정령
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "하이라이트 아이콘",
                    tint = Color(0xFF555555),
                    modifier = Modifier.size(20.dp)
                )
            }
            Spacer(modifier = Modifier.width(8.dp))

            Column {
                Text(
                    text = "밑줄: X | 필기: O | 찢김: X",
                    fontSize = 13.sp,
                    color = Color(0xFF555555)
                )
            }
        }
    }

}