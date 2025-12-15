package com.example.book.Screens.Search

import androidx.compose.foundation.background
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.book.model.Book

@Composable
fun ResultCardsRow(
    books: List<Book>,
    onCardClick: (Book) -> Unit
) {
    Column {
        Text(
            text = "검색 결과",
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFFB5D350)
        )
        Spacer(modifier = Modifier.height(12.dp))

        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(books) { book ->

                ImageCard(
                    imageUrl = book.imageUrl,
                    title = book.title,
                    subtitle = "${book.condition} · ${book.tradeMethod} 가능",
                    modifier = Modifier
                        .width(160.dp)
                        .height(210.dp),
                    onClick = { onCardClick(book) }
                )
            }
        }
    }
}


@Composable
private fun ImageCard(
    imageUrl: String,
    title: String,
    subtitle: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier.clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {

            // 책 이미지
            AsyncImage(
                model = imageUrl,
                contentDescription = title,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            // 아래쪽에 어두운 그라데이션
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color(0xAA000000)
                            ),
                            startY = 80f
                        )
                    )
            )

            // 제목 + 상태/거래 방식 (왼쪽 아래 정렬)
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = title,
                    color = Color.White,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 1,                               // 제목은 한 줄만
                    overflow = TextOverflow.Ellipsis            // 길면 … 처리
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = subtitle,                            // "상 · 직거래 가능" 같은 거
                    color = Color.White,
                    fontSize = 13.sp,
                    maxLines = 1
                )
            }
        }
    }
}
