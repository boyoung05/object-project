package com.example.book.Screens.Search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.book.Screens.BookInfo.BookInfoScreen
import com.example.book.data.Book
import com.example.book.data.dummyBooks

@Composable
fun SearchScreen(navController: NavController) {
    var showBookInfo by remember { mutableStateOf(false) }
    var query by remember { mutableStateOf("") }

    // 필터 상태 모음
    var selectedCondition by remember {mutableStateOf("")} // 상, 중, 하
    var selectedMethod by remember { mutableStateOf("") } // 직거래, 택배

    // 선택된 책
    var selectedBook by remember { mutableStateOf<Book?>(null) }

    // 전체 책 목록
    val allBooks = remember { dummyBooks }

    // 필터 + 정렬 적용한 결과 리스트
    val filteredBooks = allBooks
        .filter { book ->
            query.isBlank() ||
                    book.title.contains(query, ignoreCase = true) ||
                    book.author.contains(query, ignoreCase = true)
        }
        .filter { book ->
            selectedCondition.isBlank() || book.condition == selectedCondition
        }
        .filter { book ->
            selectedMethod.isBlank() || book.method == selectedMethod
        }

        // 스크롤 가능하게
        val scrollState = rememberScrollState()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF6F6F6))
                .verticalScroll(scrollState)
                .padding(30.dp) // 화면 전체 패딩
        ) {
            // 1. 검색 바 영역
            SearchTopBar(
                query = query,
                onQueryChange = { query = it },
                onSearch = { keyword ->
                    // 검색 로직
                    println("검색 실행: $keyword")
                }
            )
            // 중간 여백
            Spacer(modifier = Modifier.height(16.dp))
            // 2. 필터 영역
            FilterSection(
                selectedCondition = selectedCondition,
                onConditionChange = {selectedCondition = it},
                selectedMethod = selectedMethod,
                onMethodChange = {selectedMethod = it}
            )
            Spacer(modifier = Modifier.height(24.dp))

            // 3. 카드형 검색 결과 두 개 정도
            ResultCardsRow(
                books = filteredBooks,
                onCardClick = { book ->
                    navController.navigate("bookinfo/${book.id}") // 상세 화면 페이지(bookinfo)로 navigate
                }
            )
            Spacer(modifier = Modifier.height(24.dp))

            // 5. 정렬 기준 + 카드보기/목록보기 버튼 자리
            // SortSection()

        }
    }