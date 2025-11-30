package com.example.book.Screens.BookInfo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.book.data.Book
import com.example.book.data.dummyBooks

@Composable
fun BookInfoScreen(
    navController: NavController,
    bookId: Int
) {
    val scrollState = rememberScrollState()

    // 전달받은 bookId로 실제 책 찾기
    val book: Book? = dummyBooks.firstOrNull { it.id == bookId }

    // 책을 못 찾았을 때 대비
    if (book == null) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF6F6F6))
                .padding(30.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("책 정보를 찾을 수 없습니다.")
        }
        return
    }

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
        BookInfoCard(book = book)
        Spacer(modifier = Modifier.height(24.dp))

        // 2. 상태 표시 섹션
        //StatusSection()
        //Spacer(modifier = Modifier.height(24.dp))

        // 3. 하이라이트 정보
        HighlightSection()
        Spacer(modifier = Modifier.height(16.dp))

        // 4. 소유자 정보
        OwnerSection()
        Spacer(modifier = Modifier.height(32.dp))

        // 5. 교환 제안하기 버튼
        ExchangeButton(navController = navController)
        Spacer(modifier = Modifier.height(16.dp))
    }
}