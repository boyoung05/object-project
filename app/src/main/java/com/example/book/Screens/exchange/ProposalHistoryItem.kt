package com.example.book.Screens.exchange.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ProposalHistoryItem(date: String, status: String, detail: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, RoundedCornerShape(12.dp))
            .padding(16.dp)
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(date, fontSize = 13.sp, color = Color.Gray)
            Text(status, fontWeight = FontWeight.SemiBold)
        }

        Spacer(Modifier.height(4.dp))

        Text(detail, fontSize = 14.sp)
    }
    Spacer(Modifier.height(12.dp))
}
