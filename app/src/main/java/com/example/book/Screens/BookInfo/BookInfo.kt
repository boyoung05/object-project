package com.example.book.Screens.BookInfo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BookInfoScreen() {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF6F6F6))
            .verticalScroll(scrollState)
            .padding(30.dp)
    ) {
        // 상단 타이틀
        Text(
            text = "책 정보",
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFF222222)
        )

        Spacer(modifier = Modifier.height(12.dp))

        // 1. 책 카드 영역
        BookInfoCard()
        Spacer(modifier = Modifier.height(24.dp))

        // 2. 상태 표시 섹션
        StatusSection()
        Spacer(modifier = Modifier.height(24.dp))

        // 3. 하이라이트 정보
        HighlightSection()
        Spacer(modifier = Modifier.height(16.dp))

        // 4. 소유자 정보
        OwnerSection()
        Spacer(modifier = Modifier.height(32.dp))

        // 5. 교환 제안하기 버튼
        ExchangeButton()
        Spacer(modifier = Modifier.height(16.dp))
    }
}