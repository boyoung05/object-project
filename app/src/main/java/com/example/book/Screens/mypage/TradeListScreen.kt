package com.example.book.Screens.mypage

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp

// -------------------------------------------------------------------
// 데이터 모델
// -------------------------------------------------------------------
data class BookItem(
    val id: Int,
    val title: String,
    val isCompleted: Boolean = false
)

// -------------------------------------------------------------------
// 거래 완료 화면
// -------------------------------------------------------------------
@Composable
fun TradeListScreen() {

    // 로컬 상태로 책 목록 보관
    var bookList by remember {
        mutableStateOf(
            listOf(
                BookItem(1, "알고리즘 기초"),
                BookItem(2, "운영체제 요약노트"),
                BookItem(3, "자바의 정석")
            )
        )
    }

    // 화면 UI
    Column(modifier = Modifier.padding(20.dp)) {

        Text(
            text = "거래 완료 관리",
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(modifier = Modifier.height(20.dp))

        // 리스트 UI (LazyColumn)
        LazyColumn {
            items(bookList) { book ->

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 12.dp),
                    horizontalArrangement = Arrangement.Start
                ) {

                    // 체크박스 : 거래 완료 상태 토글
                    Checkbox(
                        checked = book.isCompleted,
                        onCheckedChange = { checked ->
                            bookList = bookList.map {
                                if (it.id == book.id) it.copy(isCompleted = checked) else it
                            }
                        }
                    )

                    // 책 제목
                    Text(
                        text = book.title,
                        style = MaterialTheme.typography.bodyLarge.copy(
                            textDecoration =
                                if (book.isCompleted) TextDecoration.LineThrough else TextDecoration.None,
                            color = if (book.isCompleted) Color.Gray else Color.Black
                        )
                    )
                }

                Divider(color = Color(0xFFEAEAEA))
            }
        }
    }
}


