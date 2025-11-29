package com.example.book.Screens.Search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun SearchScreen(
    navController: NavHostController
) {
    // 스크롤 가능하게
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF6F6F6))
            .verticalScroll(scrollState)
            .padding(30.dp)
    ) {
        SearchTopBar()
        Spacer(modifier = Modifier.height(16.dp))

        FilterSection()
        Spacer(modifier = Modifier.height(24.dp))

        ResultTitle()
        Spacer(modifier = Modifier.height(12.dp))

        ResultCardsRow(
            onCardClick = {
                // ✅ 카드 클릭 시 책 상세 페이지로 이동
                navController.navigate("bookinfo")
            }
        )

        Spacer(modifier = Modifier.height(24.dp))

        SortSection()
    }
}
