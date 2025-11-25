package com.example.book.Screens.exchange.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun BookCard() {
    Column(
        modifier = Modifier
            .width(95.dp)
            .background(Color.White, RoundedCornerShape(12.dp))
            .padding(10.dp)
    ) {
        Box(
            modifier = Modifier
                .height(110.dp)
                .fillMaxWidth()
                .background(Color.Black, RoundedCornerShape(8.dp))
        )

        Spacer(Modifier.height(6.dp))

        Box(
            modifier = Modifier
                .size(24.dp)
                .background(Color(0xFFEFEFEF), RoundedCornerShape(12.dp)),
            contentAlignment = Alignment.Center
        ) {
            Text("+", fontWeight = FontWeight.Bold)
        }
    }
}
