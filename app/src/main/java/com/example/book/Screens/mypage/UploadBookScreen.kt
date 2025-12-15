package com.example.book.Screens.mypage

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.book.model.Book
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import java.util.UUID

@Composable
fun UploadBookScreen(navController: NavController) {

    // -------------------- 입력 상태 --------------------
    var title by remember { mutableStateOf("") }
    var author by remember { mutableStateOf("") }
    var publisher by remember { mutableStateOf("") }

    val conditionOptions = listOf("상", "중", "하")
    val tradeMethodOptions = listOf("직거래", "택배")
    val categoryOptions = listOf("에세이", "소설", "교재")

    var condition by remember { mutableStateOf("") }
    var tradeMethod by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("") }

    var imageUri by remember { mutableStateOf<Uri?>(null) }
    var isLoading by remember { mutableStateOf(false) }

    val context = LocalContext.current

    // -------------------- Firebase --------------------
    val db = FirebaseFirestore.getInstance()
    val storage = FirebaseStorage.getInstance()

    // -------------------- 이미지 선택 --------------------
    val imagePick = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri ->
        imageUri = uri
    }

    // -------------------- UI --------------------
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(20.dp)
    ) {

        Text("📚 책 등록", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("책 제목") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = author,
            onValueChange = { author = it },
            label = { Text("저자") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = publisher,
            onValueChange = { publisher = it },
            label = { Text("출판사") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(20.dp))

        // -------------------- 도서 상태 --------------------
        Text("도서 상태")
        Spacer(modifier = Modifier.height(8.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            conditionOptions.forEach {
                OutlinedButton(
                    modifier = Modifier.weight(1f),
                    onClick = { condition = it },
                    colors = if (condition == it)
                        ButtonDefaults.outlinedButtonColors(
                            containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.15f)
                        )
                    else ButtonDefaults.outlinedButtonColors()
                ) { Text(it) }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // -------------------- 거래 방식 --------------------
        Text("거래 방식")
        Spacer(modifier = Modifier.height(8.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            tradeMethodOptions.forEach {
                OutlinedButton(
                    modifier = Modifier.weight(1f),
                    onClick = { tradeMethod = it },
                    colors = if (tradeMethod == it)
                        ButtonDefaults.outlinedButtonColors(
                            containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.15f)
                        )
                    else ButtonDefaults.outlinedButtonColors()
                ) { Text(it) }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // -------------------- 도서 종류 --------------------
        Text("도서 종류")
        Spacer(modifier = Modifier.height(8.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            categoryOptions.forEach {
                OutlinedButton(
                    modifier = Modifier.weight(1f),
                    onClick = { category = it },
                    colors = if (category == it)
                        ButtonDefaults.outlinedButtonColors(
                            containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.15f)
                        )
                    else ButtonDefaults.outlinedButtonColors()
                ) { Text(it) }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // -------------------- 이미지 --------------------
        Button(
            onClick = { imagePick.launch("image/*") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(if (imageUri != null) "사진 다시 선택하기" else "책 사진 선택하기")
        }

        Spacer(modifier = Modifier.height(24.dp))

        // -------------------- 등록 버튼 --------------------
        Button(
            modifier = Modifier.fillMaxWidth(),
            enabled = !isLoading,
            onClick = {

                // 🔐 로그인 상태 재확인 (핵심)
                val currentUser = FirebaseAuth.getInstance().currentUser
                if (currentUser == null) {
                    Toast.makeText(context, "로그인 후 책을 등록할 수 있어요.", Toast.LENGTH_SHORT).show()
                    return@Button
                }

                val ownerId = currentUser.uid

                // 유효성 검사
                if (title.isBlank() || author.isBlank() || publisher.isBlank()) {
                    Toast.makeText(context, "제목 / 저자 / 출판사는 필수입니다.", Toast.LENGTH_SHORT).show()
                    return@Button
                }

                if (condition.isBlank() || tradeMethod.isBlank() || category.isBlank()) {
                    Toast.makeText(context, "모든 선택 항목을 골라주세요.", Toast.LENGTH_SHORT).show()
                    return@Button
                }

                isLoading = true
                val newId = UUID.randomUUID().toString()

                // -------------------- 저장 함수 --------------------
                fun saveBook(imageUrl: String) {
                    val book = Book(
                        id = newId,
                        title = title,
                        author = author,
                        publisher = publisher,
                        condition = condition,
                        tradeMethod = tradeMethod,
                        category = category,
                        imageUrl = imageUrl,
                        ownerId = ownerId
                    )

                    db.collection("books")
                        .document(newId)
                        .set(book)
                        .addOnSuccessListener {
                            isLoading = false
                            Toast.makeText(context, "책이 등록되었습니다.", Toast.LENGTH_SHORT).show()
                            navController.popBackStack()
                        }
                        .addOnFailureListener { e ->
                            isLoading = false
                            Toast.makeText(context, "등록 실패: ${e.message}", Toast.LENGTH_SHORT).show()
                        }
                }

                // -------------------- 이미지 처리 --------------------
                val picked = imageUri
                if (picked != null) {
                    val imageRef = storage.reference.child("bookImages/$newId.jpg")
                    imageRef.putFile(picked)
                        .addOnSuccessListener {
                            imageRef.downloadUrl
                                .addOnSuccessListener { uri ->
                                    saveBook(uri.toString())
                                }
                                .addOnFailureListener {
                                    isLoading = false
                                    Toast.makeText(context, "이미지 URL 실패", Toast.LENGTH_SHORT).show()
                                }
                        }
                        .addOnFailureListener {
                            isLoading = false
                            Toast.makeText(context, "이미지 업로드 실패", Toast.LENGTH_SHORT).show()
                        }
                } else {
                    saveBook("")
                }
            }
        ) {
            Text(if (isLoading) "등록 중..." else "등록하기")
        }
    }
}
