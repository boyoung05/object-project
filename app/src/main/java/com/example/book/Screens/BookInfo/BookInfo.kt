package com.example.book.Screens.BookInfo

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.book.model.Book
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun BookInfoScreen(
    navController: NavController,
    bookId: String
) {
    val scrollState = rememberScrollState()
    val db = remember { FirebaseFirestore.getInstance() }

    var book by remember { mutableStateOf<Book?>(null) }
    var ownerNickname by remember { mutableStateOf<String?>(null) }
    var ownerSchool by remember { mutableStateOf<String?>(null) }

    var isLoading by remember { mutableStateOf(true) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(bookId) {
        db.collection("books")
            .document(bookId)
            .get()
            .addOnSuccessListener { doc ->
                val fetchedBook = doc.toObject(Book::class.java)
                book = fetchedBook

                // 🔥 책 소유자 정보 조회
                fetchedBook?.ownerId?.let { uid ->
                    db.collection("users")
                        .document(uid)
                        .get()
                        .addOnSuccessListener { userDoc ->
                            ownerNickname = userDoc.getString("nickname")
                            ownerSchool = userDoc.getString("school")
                            isLoading = false
                        }
                        .addOnFailureListener {
                            isLoading = false
                        }
                } ?: run {
                    isLoading = false
                }
            }
            .addOnFailureListener { e ->
                errorMessage = e.message
                isLoading = false
            }
    }

    when {
        isLoading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        errorMessage != null || book == null -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text("책 정보를 불러올 수 없습니다.")
            }
        }

        else -> {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState)
                    .padding(30.dp)
            ) {

                Text(
                    text = "책 정보",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )

                Spacer(modifier = Modifier.height(12.dp))

                BookInfoCard(book = book!!)
                Spacer(modifier = Modifier.height(24.dp))

                HighlightSection()
                Spacer(modifier = Modifier.height(16.dp))

                OwnerSection(
                    nickname = ownerNickname ?: "알 수 없음",
                    school = ownerSchool ?: "학교 정보 없음"
                )

                Spacer(modifier = Modifier.height(32.dp))

                // ✅ 여기 핵심 수정
                ExchangeButton(
                    navController = navController,
                    opponentUid = book!!.ownerId
                )
            }
        }
    }
}
