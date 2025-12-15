package com.example.book.Screens.exchange.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.book.model.Book

@Composable
fun BookCard(
    book: Book?,
    selected: Boolean,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .width(95.dp)
            .background(
                if (selected) Color(0xFFDFF0B6) else Color.White,
                RoundedCornerShape(12.dp)
            )
            .padding(10.dp)
            .clickable { onClick() }
    ) {

        Box(
            modifier = Modifier
                .height(110.dp)
                .fillMaxWidth()
                .background(Color(0xFFE0E0E0), RoundedCornerShape(8.dp)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = book?.title ?: "+",
                fontSize = 12.sp,
                fontWeight = if (book == null) FontWeight.Bold else FontWeight.Normal,
                maxLines = 2
            )
        }
    }
}
