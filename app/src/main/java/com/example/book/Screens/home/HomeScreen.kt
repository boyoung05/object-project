package com.example.book.Screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.book.Screens.home.components.ActionButtons
import com.example.book.Screens.home.components.HomeTopSlider
import com.example.book.Screens.home.components.KeywordItem

@Composable
fun HomeScreen(navController: NavHostController) {

    // 🔹 Home 전용 ViewModel (Firestore에서 book 3권 가져옴)
    val viewModel: HomeViewModel = viewModel()
    val books by viewModel.books.collectAsState()

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
    ) {

        Spacer(modifier = Modifier.height(24.dp))

        // ✅ 슬라이드는 padding 없이 전체 폭 사용
        HomeTopSlider(books = books)

        Spacer(modifier = Modifier.height(30.dp))

        // ✅ 나머지 UI만 padding 적용
        Column(
            modifier = Modifier.padding(horizontal = 20.dp)
        ) {

            Text(
                text = "이번주 인기 키워드",
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(modifier = Modifier.height(16.dp))

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

            ActionButtons(navController)

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}
