package com.example.book.Screens.Search

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun FilterSection () {
    Column {
        Text(
            text = "필터",
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFF333333)
        )
        Spacer(modifier = Modifier.height(12.dp))

        // 간단한 상태 필터 (상/중/하)
        Text(
            text="도서 상태",
            fontSize = 15.sp,
            color = Color(0xFF666666)
        )
        Spacer(modifier = Modifier.height(8.dp))

        var selectedCondition = remember { mutableStateOf("")}

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            FilterButton(
                text = "상",
                selected = selectedCondition.value == "상",
                onClick = {selectedCondition.value = "상"}
            )
            FilterButton(
                text = "중",
                selected = selectedCondition.value == "중",
                onClick = {selectedCondition.value = "중"}
            )
            FilterButton(
                text = "하",
                selected = selectedCondition.value == "하",
                onClick = {selectedCondition.value = "하"}
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "거래 방식",
            fontSize = 15.sp,
            color = Color(0xFF666666)
        )
        Spacer(modifier = Modifier.height(8.dp))

        var selectedMethod = remember { mutableStateOf("")}

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            FilterButton(
                text = "직거래",
                selected = selectedMethod.value == "직거래",
                onClick = {selectedMethod.value = "직거래"}
            )
            FilterButton(
                text = "택배",
                selected = selectedMethod.value == "택배",
                onClick = {selectedMethod.value = "택배"}
            )
        }
    }
}

@Composable
fun FilterButton(
    text: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    val backgroundColor = if(selected) Color(0xFF4CAF50) else Color.White
    val textColor = if(selected) Color.White else Color(0xFF555555)

    Box(
        modifier = Modifier
            .height(32.dp)
            .padding(vertical = 2.dp)
            .border(
                width = 1.dp,
                color = if(selected) Color.White else Color(0xFFE0E0E0),
                shape = RoundedCornerShape(16.dp)
            )
            .background(backgroundColor, RoundedCornerShape(16.dp))
            .clickable {onClick()}
            .padding(horizontal = 14.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(text = text, color = textColor, fontSize = 13.sp)
    }
}
