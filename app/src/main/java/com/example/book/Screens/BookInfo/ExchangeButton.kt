package com.example.book.Screens.BookInfo

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ExchangeButton() {
    Button(
        onClick = {/* 나중에 교환 제안 페이지로 넘어가게 */},
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFFB9D86B),
            contentColor = Color(0xFF222222)
        ),
        shape = RoundedCornerShape(10.dp)
    ) {
        Text(
            text = "교환 제안하기",
            fontSize = 15.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}
