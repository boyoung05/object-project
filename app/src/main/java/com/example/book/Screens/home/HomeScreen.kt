package com.example.book.Screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.book.Screens.home.components.BookCarousel
import com.example.book.Screens.home.components.KeywordItem
import com.example.book.Screens.home.components.ActionButtons

@Composable
fun HomeScreen(navController: NavHostController) {

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
            .padding(horizontal = 20.dp)
    ) {

        Spacer(Modifier.height(24.dp))

        BookCarousel()

        Spacer(Modifier.height(30.dp))

        Text(
            text = "이번주 인기 키워드",
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(Modifier.height(16.dp))

        KeywordItem(
            title = "토익",
            tag = "토익 문제집",
            description = "목표 점수까지 가장 빠른 길"
        )

        KeywordItem(
            title = "에세이",
            tag = "자기 계발",
            description = "일상에서 건진 작은 진심"
        )

        Spacer(Modifier.height(24.dp))

        ActionButtons(navController)

        Spacer(Modifier.height(24.dp))
    }
}
