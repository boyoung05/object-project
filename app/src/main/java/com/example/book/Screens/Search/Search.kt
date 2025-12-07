package com.example.book.Screens.Search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.book.model.Book
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun SearchScreen(navController: NavController) {

    var query by remember { mutableStateOf("") }

    // 필터 상태 모음
    var selectedCondition by remember {mutableStateOf("")} // 상, 중, 하
    var selectedMethod by remember { mutableStateOf("") } // 직거래, 택배

    // Firestore에서 가져온 전체 책 목록
    var allBooks by remember { mutableStateOf<List<Book>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    val scrollState = rememberScrollState()
    val db = remember { FirebaseFirestore.getInstance() }

    // Firestore에서 전체 책 가져오기
    LaunchedEffect(Unit) {
        db.collection("books")
            .get()
            .addOnSuccessListener { a ->
                val list = a.documents.mapNotNull { b ->
                    b.toObject(Book::class.java)
                }
                allBooks = list
                isLoading = false
            }
            .addOnFailureListener { e ->
                isLoading = false
                errorMessage = e.message
            }
    }

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
            selectedMethod.isBlank() || book.tradeMethod == selectedMethod
        }

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

            // 3. 카드형 결과
            when {
                isLoading -> {
                    CircularProgressIndicator()
                }
                errorMessage != null -> {
                    Text(text = "에러 : $errorMessage")
                }
                filteredBooks.isEmpty() -> {
                    Text(text = "조건에 맞는 책이 없어요.")
                }
                else -> {
                    ResultCardsRow(
                        books = filteredBooks,
                        onCardClick = { book ->
                            navController.navigate("bookinfo/${book.id}") // 상세 화면 페이지(bookinfo)로 navigate
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // 5. 정렬 기준 + 카드보기/목록보기 버튼 자리
            // SortSection()

        }
    }