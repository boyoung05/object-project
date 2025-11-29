package com.example.book.Screens.Search

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun ResultTitle() {
    Text(
        text = buildAnnotatedString {
            append("검색")
            pushStyle(
                SpanStyle(
                    color = Color(0xFF4CAF50),
                    fontWeight = FontWeight.Bold
                )
            )
            append("걸과")
            pop()
        },
        fontSize = 18.sp,
        fontWeight = FontWeight.SemiBold,
        color = Color(0xFF222222)
    )
}
