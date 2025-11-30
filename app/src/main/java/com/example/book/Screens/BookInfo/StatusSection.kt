package com.example.book.Screens.BookInfo

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
// 책의 상태 정보는 API로 받을 수 없는 정보일 것 같아서
// 고정 UI로 하는 수 밖에 없을 것 같음
fun StatusSection() {
    Column{
        Text(
            text = "상태 표시",
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFF222222)
        )
        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "도서 상태",
            fontSize = 13.sp,
            color = Color(0xFF666666)
        )
        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // 상
            Box(
                modifier = Modifier
                    .height(32.dp)
                    .border(
                        width = 1.dp,
                        color = Color(0xFFB5D350),
                        shape = RoundedCornerShape(8.dp)
                    )
                    .background(Color(0xFFB5D350), RoundedCornerShape(8.dp))
                    .padding(horizontal = 16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "상",
                    fontSize = 13.sp,
                    color = Color.White
                )
            }
            // 중
            Box(
                modifier = Modifier
                    .height(32.dp)
                    .border(
                        width = 1.dp,
                        color = Color(0xFFE0E0E0),
                        shape = RoundedCornerShape(8.dp)
                    )
                    .background(Color.White, RoundedCornerShape(8.dp))
                    .padding(horizontal = 16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "중",
                    fontSize = 13.sp,
                    color = Color(0xFF555555)
                )
            }
            // 하
            Box(
                modifier = Modifier
                    .height(32.dp)
                    .border(
                        width = 1.dp,
                        color = Color(0xFFE0E0E0),
                        shape = RoundedCornerShape(8.dp)
                    )
                    .background(Color.White, RoundedCornerShape(8.dp))
                    .padding(horizontal = 16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "하",
                    fontSize = 13.sp,
                    color = Color(0xFF555555)
                )
            }

        }
    }
}