package com.example.book.Screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.book.Screens.home.components.ActionButtons
import com.example.book.Screens.home.components.BookCarousel
import com.example.book.Screens.home.components.KeywordItem
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FieldPath
import kotlinx.coroutines.tasks.await

@Composable
fun HomeScreen(navController: NavHostController) {

    var imageUrls by remember { mutableStateOf<List<String>>(emptyList()) }
    var titles by remember { mutableStateOf<List<String>>(emptyList()) }
    var tags by remember { mutableStateOf<List<String>>(emptyList()) }

    LaunchedEffect(Unit) {
        runCatching {
            val db = FirebaseFirestore.getInstance()

            // ✅ 콘솔 맨 위 3개 (문서 ID 기준 정렬)
            val snap = db.collection("books")
                .orderBy(FieldPath.documentId())
                .limit(3)
                .get()
                .await()

            imageUrls = snap.documents.mapNotNull { it.getString("imageUrl") }
            titles = snap.documents.map { it.getString("title") ?: "" }
            tags = snap.documents.map {
                val condition = it.getString("condition") ?: ""
                val tradeMethod = it.getString("tradeMethod") ?: ""
                "${condition} · ${tradeMethod} 가능"
            }
        }.onFailure {
            imageUrls = emptyList()
            titles = emptyList()
            tags = emptyList()
        }
    }

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
            .padding(horizontal = 20.dp)
    ) {
        Spacer(Modifier.height(24.dp))

        BookCarousel(
            imageUrls = imageUrls,
            titles = titles,
            tags = tags
        )

        Spacer(Modifier.height(30.dp))

        Text(
            text = "이번주 인기 키워드",
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(Modifier.height(16.dp))

        KeywordItem(
            title = "토익",
            tag = "토익 문제집",
            description = "목표 점수까지 가장 빠른 길"
        )

        KeywordItem(
            title = "에세이",
            tag = "자기 계발",
            description = "일상에서 건진 작은 진심"
        )

        Spacer(Modifier.height(24.dp))

        ActionButtons(navController)

        Spacer(Modifier.height(24.dp))
    }
}
