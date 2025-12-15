package com.example.book.Screens.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.book.model.Book

@Composable
fun HomeTopSlider(
    books: List<Book>
) {
    if (books.isEmpty()) return

    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = { books.size }
    )

    Column {

        Spacer(modifier = Modifier.height(16.dp))

        HorizontalPager(
            state = pagerState,
            contentPadding = PaddingValues(horizontal = 32.dp), // 🔥 좌우 카드 보이게
            pageSpacing = 16.dp,                                 // 🔥 카드 사이 간격
            modifier = Modifier
                .fillMaxWidth()
                .height(260.dp)                                  // 🔥 핵심: 큰 카드 높이
        ) { page ->

            val book = books[page]

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color(0xFFF0EEEE),
                                Color(0xFFD6D3D3)
                            )
                        ),
                        shape = RoundedCornerShape(28.dp)
                    ),
                contentAlignment = Alignment.BottomStart
            ) {
                Column(
                    modifier = Modifier.padding(20.dp)
                ) {
                    Text(
                        text = book.title,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = "${book.condition} · ${book.tradeMethod}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color(0xFF666666)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // 인디케이터
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(books.size.coerceAtMost(3)) { index ->
                Box(
                    modifier = Modifier
                        .size(6.dp)
                        .background(
                            if (pagerState.currentPage == index)
                                Color(0xFF9C8EFF)
                            else
                                Color(0xFFDADADA),
                            RoundedCornerShape(50)
                        )
                )
                Spacer(modifier = Modifier.width(6.dp))
            }
        }
    }
}
// test commit
