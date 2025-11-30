package com.example.book.Screens.BookInfo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BookInfoCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 30.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)

    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // 상태 뱃지
            Box(
                modifier = Modifier
                    .background(Color(0xFFE5F5C2), RoundedCornerShape(8.dp))
                    .padding(horizontal = 10.dp, vertical = 4.dp)
            ) {
                Text(
                    text = "상태: 상",
                    fontSize = 12.sp,
                    color = Color(0xFF6C8C1B),
                    fontWeight = FontWeight.SemiBold
                )
            }
            Spacer(modifier = Modifier.height(12.dp))

            // 책 표지 + 설명
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp)
                    .background(Color(0xFFF0F0F0), RoundedCornerShape(12.dp)),
                contentAlignment = Alignment.Center
            ) {
                // 나중에 실제 이미지 들어갈 자리
                Text(
                    text = "책 표지\n(이미지 자리)",
                    textAlign = TextAlign.Center,
                    fontSize = 13.sp,
                    color = Color(0xFF888888)
                )
            }
            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "책 제목: 스토너",
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF333333)
            )
            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "저자: 존 윌리엄스 | 출판사: 알에이치코리아 |",
                fontSize = 12.sp,
                color = Color(0xFF777777)
            )
        }
    }
}
