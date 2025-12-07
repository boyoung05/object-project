package com.example.book.Screens.BookInfo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun OwnerSection() {
    Column{
        Text(
            text = "소유자 닉네임",
            fontSize = 13.sp,
            color = Color(0xFF666666)
        )
        Spacer(modifier = Modifier.height(8.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            // 프로필 이미지 자리
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(Color(0xFFEDEDED), CircleShape)
            )
            Spacer(modifier = Modifier.width(10.dp))

            Column {
                Text(
                    text = "소유자 닉네임",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF333333)
                )
                Text(
                    text = "소속 학교: 한국항공대학교",
                    fontSize = 12.sp,
                    color = Color(0xFF777777)
                )
            }
        }
    }
}
