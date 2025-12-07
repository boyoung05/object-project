package com.example.book.Screens.Search

import androidx.compose.foundation.background
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import androidx.compose.ui.layout.ContentScale
import com.example.book.model.Book
import androidx.compose.ui.text.style.TextOverflow

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
                    book = book,
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
    book: Book,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier.clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {

            // Firebase Storage 이미지 로딩
            AsyncImage(
                model = book.imageUrl,          // Firestore에 저장된 downloadUrl
                contentDescription = book.title,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            // 아래쪽 그라데이션
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

            // 제목 + 상태/거래 방식
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp),
                contentAlignment = Alignment.BottomStart
            ) {
                Column {

                    // 책 제목
                    Text(
                        text = book.title,
                        color = Color.White,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.SemiBold,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )

                    Spacer(modifier = Modifier.height((2.dp)))

                    // 상태 + 거래 방식
                    Text(
                        text = "${book.condition} · ${book.tradeMethod}",
                        color = Color(0xFFEEEEEE),
                        fontSize = 12.sp
                    )
                }

            }
        }
    }
}
