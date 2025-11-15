package com.example.project.ui.screen.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.project.R

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeBookCarousel() {
    // 페이지 수는 3개 예시
    val pagerState = rememberPagerState(pageCount = { 3 })

    HorizontalPager(
        state = pagerState,
        modifier = Modifier
            .fillMaxWidth()
            .height(320.dp)
    ) { _ ->
        Card(
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .height(300.dp)
                .fillMaxWidth()
        ) {
            // 책 표지 이미지 (임시로 ic_launcher_foreground 사용)
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = "책 이미지",
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
        }
    }
}


