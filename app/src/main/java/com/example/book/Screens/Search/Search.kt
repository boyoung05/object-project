package com.example.book.Screens.Search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.book.Screens.BookInfo.BookInfoScreen

@Composable
fun SearchScreen() {
    var showBookInfo by remember { mutableStateOf(false) }

    if (showBookInfo) {
        // 카드 눌렀을 때 보여줄 상세화면
        BookInfoScreen()
    } else {

        // 스크롤 가능하게
        val scrollState = rememberScrollState()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF6F6F6))
                .verticalScroll(scrollState)
                .padding(30.dp) // 화면 전체 패딩
        ) {
            // 1. 검색 바 영역
            SearchTopBar()
            // 중간 여백
            Spacer(modifier = Modifier.height(16.dp))
            // 2. 필터 영역
            FilterSection()
            Spacer(modifier = Modifier.height(24.dp))

            // 3. 검색 결과 타이틀
            ResultTitle()
            Spacer(modifier = Modifier.height(12.dp))

            // 4. 카드형 검색 결과 두 개 정도
            ResultCardsRow(
                onCardClick = {showBookInfo = true}
            )
            Spacer(modifier = Modifier.height(24.dp))

            // 5. 정렬 기준 + 카드보기/목록보기 버튼 자리
            SortSection()

        }
    }
}
