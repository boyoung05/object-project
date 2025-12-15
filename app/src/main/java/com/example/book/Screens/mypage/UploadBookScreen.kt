package com.example.book.Screens.mypage

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState
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

    var title by remember { mutableStateOf("") }
    var author by remember { mutableStateOf("") }
    var publisher by remember { mutableStateOf("") }

    // 선택 항목들
    val conditionOptions = listOf("상", "중", "하")
    val tradeMethodOptions = listOf("직거래", "택배")
    val categoryOptions = listOf("에세이", "소설", "교재")

    var condition by remember { mutableStateOf("") }
    var tradeMethod by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("") }

    // 선택된 이미지 Uri
    var imageUri by remember { mutableStateOf<Uri?>(null) }

    // 로딩 상태
    var isLoading by remember { mutableStateOf(false) }

    val context = LocalContext.current

    // Firebase
    val db = FirebaseFirestore.getInstance()
    val auth = FirebaseAuth.getInstance()
    val storage = FirebaseStorage.getInstance()
    val currentUser = auth.currentUser
    val ownerId = currentUser?.uid ?: "" // 현재 로그인한 사용자 uid

    // 갤러리에서 이미지 하나 선택하는 것
    val imagePick = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        imageUri = uri
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(20.dp)
    ) {
        Text(text = "📚 책 등록", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(20.dp))

        // 책 제목
        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("책 제목") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        // 저자
        OutlinedTextField(
            value = author,
            onValueChange = { author = it },
            label = { Text("저자") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        // 출판사
        OutlinedTextField(
            value = publisher,
            onValueChange = { publisher = it },
            label = { Text("출판사") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(20.dp))

        // 도서 상태 버튼 선택
        Text(text = "도서 상태", style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            conditionOptions.forEach { option ->
                val selected = (condition == option)
                OutlinedButton(
                    onClick = { condition = option },
                    modifier = Modifier.weight(1f),
                    colors = if (selected) {
                        ButtonDefaults.outlinedButtonColors(
                            containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.15f),
                            contentColor = MaterialTheme.colorScheme.primary
                        )
                    } else {
                        ButtonDefaults.outlinedButtonColors()
                    }
                ) {
                    Text(option)
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 거래 방식 버튼 선택
        Text(text = "거래 방식", style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            tradeMethodOptions.forEach { option ->
                val selected = (tradeMethod == option)
                OutlinedButton(
                    onClick = { tradeMethod = option },
                    modifier = Modifier.weight(1f),
                    colors = if (selected) {
                        ButtonDefaults.outlinedButtonColors(
                            containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.15f),
                            contentColor = MaterialTheme.colorScheme.primary
                        )
                    } else {
                        ButtonDefaults.outlinedButtonColors()
                    }
                ) {
                    Text(option)
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 도서 종류 버튼 선택
        Text(text = "도서 종류", style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            categoryOptions.forEach { option ->
                val selected = (category == option)
                OutlinedButton(
                    onClick = { category = option },
                    modifier = Modifier.weight(1f),
                    colors = if (selected) {
                        ButtonDefaults.outlinedButtonColors(
                            containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.15f),
                            contentColor = MaterialTheme.colorScheme.primary
                        )
                    } else {
                        ButtonDefaults.outlinedButtonColors()
                    }
                ) {
                    Text(option)
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // 사진 선택 버튼
        Button(
            onClick = {
                imagePick.launch("image/*")
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                if (imageUri != null) "사진 다시 선택하기"
                else "책 사진 선택하기"
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // 등록 버튼
        Button(
            modifier = Modifier.fillMaxWidth(),
            enabled = !isLoading,
            onClick = {
                // 유효성 검사
                if (title.isBlank() || author.isBlank() || publisher.isBlank()) {
                    Toast.makeText(context, "제목 / 저자 / 출판사는 필수로 입력해 주세요.", Toast.LENGTH_SHORT)
                        .show()
                    return@Button
                }

                if (condition.isBlank() || tradeMethod.isBlank() || category.isBlank()) {
                    Toast.makeText(context, "도서 상태 / 거래 방식/ 도서 종류를 선택해 주세요.", Toast.LENGTH_SHORT)
                        .show()
                    return@Button
                }

                if (ownerId.isBlank()) {
                    Toast.makeText(context, "로그인 후 책을 등록할 수 있어요.", Toast.LENGTH_SHORT).show()
                    return@Button
                }

                isLoading = true
                val newId = UUID.randomUUID().toString()

                // FireStore에 Book 저장
                fun saveBook(imageUrl: String) {
                    val newBook = Book(
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
                        .set(newBook)
                        .addOnSuccessListener {
                            isLoading = false
                            Toast.makeText(context, "책이 등록되었습니다.", Toast.LENGTH_SHORT).show()

                            // 입력값 초기화
                            title = ""
                            author = ""
                            publisher = ""
                            condition = ""
                            tradeMethod = ""
                            category = ""
                            imageUri = null

                            // 이전 화면으로 돌아가기
                            navController.popBackStack()
                        }
                        .addOnFailureListener { e ->
                            isLoading = false
                            Toast.makeText(
                                context,
                                "등록 실패: ${e.message}",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                }

                val pickedImage = imageUri

                // 1) 이미지가 선택된 경우: Storage에 먼저 업로드
                if (pickedImage != null) {
                    val go2storage = storage.reference
                    val image = go2storage.child("bookImages/$newId.jpg")

                    image.putFile(pickedImage)
                        .addOnSuccessListener {
                            // 업로드 성공 -> 다운로드 URL 가져옴
                            image.downloadUrl
                                .addOnSuccessListener { uri ->
                                    val downloadUrl = uri.toString()
                                    saveBook(downloadUrl)
                                }
                                .addOnFailureListener { e ->
                                    isLoading = false
                                    Toast.makeText(
                                        context,
                                        "이미지 URL 가져오기 실패: ${e.message}",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                        }
                        .addOnFailureListener { e ->
                            isLoading = false
                            Toast.makeText(
                                context,
                                "이미지 업로드 실패: ${e.message}",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                } else {
                    //2) 사진 업로드 하지 않은 경우
                    saveBook(imageUrl = "")
                }
            }
        ) {
            Text(if (isLoading) "등록 중" else "등록하기")
        }
    }
}
