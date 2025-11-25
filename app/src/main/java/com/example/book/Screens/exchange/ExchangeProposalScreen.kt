package com.example.book.Screens.exchange

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.book.Screens.exchange.components.BookCard
import com.example.book.Screens.exchange.components.CategoryChip
import com.example.book.Screens.exchange.components.TradeToggleItem
import com.example.book.Screens.exchange.components.ProposalHistoryItem

@Composable
fun ExchangeProposalScreen() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8F8F8))
            .verticalScroll(rememberScrollState())
            .padding(20.dp)
    ) {

        Text(
            text = "교환 제안",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(Modifier.height(20.dp))

        Text("내 책 선택 (최대 3권)", fontWeight = FontWeight.SemiBold)

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            repeat(3) {
                BookCard()
            }
        }

        Spacer(Modifier.height(8.dp))
        Text("선택됨: 없음 (카드를 눌러 선택하세요)", fontSize = 12.sp, color = Color.Gray)

        Spacer(Modifier.height(28.dp))

        Text("조건 비교", fontWeight = FontWeight.SemiBold)
        Spacer(Modifier.height(10.dp))
        Text("교환점수")

        Slider(value = 0.7f, onValueChange = {}, enabled = false)

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            CategoryChip("에세이")
            CategoryChip("소설")
            CategoryChip("교재")
        }

        Spacer(Modifier.height(28.dp))

        Text("거래 방식 선택", fontWeight = FontWeight.SemiBold)

        Spacer(Modifier.height(10.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            TradeToggleItem("직거래", true)
            TradeToggleItem("택배", false)
        }

        Spacer(Modifier.height(16.dp))

        OutlinedTextField(
            value = "",
            onValueChange = {},
            placeholder = { Text("예: 항공대 중앙도서관 앞") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(10.dp))

        OutlinedTextField(
            value = "",
            onValueChange = {},
            placeholder = { Text("예: 5월 10일 (토) 15:00") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(16.dp))

        Button(
            onClick = {},
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFB9D86B),
                contentColor = Color.Black
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
        ) {
            Text("제안 보내기", fontWeight = FontWeight.SemiBold)
        }

        Spacer(Modifier.height(32.dp))

        Text("제안 내역", fontWeight = FontWeight.SemiBold)

        Spacer(Modifier.height(10.dp))

        ProposalHistoryItem("05.08 14:10", "대기중", "토익 리딩 ↔ 영어 에세이")
        ProposalHistoryItem("05.06 18:32", "수락", "자료구조 ↔ C 언어")
        ProposalHistoryItem("05.02 11:04", "거절", "운영체제 ↔ 네트워크")
    }
}
