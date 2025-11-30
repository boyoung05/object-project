package com.example.book.Screens.Search

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.book.Screens.Search.FilterButton

@Composable
fun SortSection() {
    Column {
        Text(
            text = "정렬 기준",
            fontSize = 15.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFF333333)
        )
        Spacer(modifier = Modifier.height(8.dp))

        var selectedSortCondition = remember { mutableStateOf("")}


        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            FilterButton(
                text= "거리",
                selected = selectedSortCondition.value == "거리",
                onClick = {selectedSortCondition.value = "거리"}
            )
            FilterButton(
                text = "최신",
                selected = selectedSortCondition.value == "최신",
                onClick = {selectedSortCondition.value = "최신"}
            )
            FilterButton(
                text = "인기",
                selected = selectedSortCondition.value == "인기",
                onClick = {selectedSortCondition.value = "인기"}
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(44.dp)
                    .background(Color.White, RoundedCornerShape(12.dp))
                    .border(
                        1.dp,
                        Color(0xFFE0E0E0),
                        RoundedCornerShape(12.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "카드 보기", textAlign = TextAlign.Center)
            }
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(44.dp)
                    .background(Color(0xFFFFF4C2), RoundedCornerShape(12.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "목록 보기", textAlign = TextAlign.Center)
            }
        }
    }
}
