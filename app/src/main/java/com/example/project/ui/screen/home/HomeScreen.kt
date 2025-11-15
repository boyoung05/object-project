package com.example.project.ui.screen.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun HomeScreen(
    navController: NavHostController
) {
    Scaffold(
        bottomBar = {
            BottomNavBar(navController = navController)
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .fillMaxWidth()
        ) {
            Spacer(modifier = Modifier.height(20.dp))

            // 상단 책 카루셀
            HomeBookCarousel()

            Spacer(modifier = Modifier.height(30.dp))

            // 이번주 인기 키워드 제목
            Text(
                text = "이번주 인기 키워드",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(horizontal = 20.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // 인기 키워드 아이템들
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

            Spacer(modifier = Modifier.height(24.dp))

            // 하단 버튼 2개
            ActionButtons()

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}


