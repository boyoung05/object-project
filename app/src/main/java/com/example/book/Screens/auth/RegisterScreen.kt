package com.example.book.ui.screens.auth

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.book.auth.AuthViewModel

@Composable
fun RegisterScreen(
    navController: NavHostController,
    viewModel: AuthViewModel = viewModel()
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    // 🔥 새로 추가된 부분
    var nickname by remember { mutableStateOf("") }
    var school by remember { mutableStateOf("") }

    var errorMsg by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text("회원가입", fontSize = 28.sp, fontWeight = FontWeight.Bold)

        Spacer(Modifier.height(20.dp))

        // 이메일
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("이메일") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(12.dp))

        // 비밀번호
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("비밀번호") },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(Modifier.height(12.dp))

        // 닉네임
        OutlinedTextField(
            value = nickname,
            onValueChange = { nickname = it },
            label = { Text("닉네임") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(12.dp))

        // 학교
        OutlinedTextField(
            value = school,
            onValueChange = { school = it },
            label = { Text("학교") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(16.dp))

        // 회원가입 버튼
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                if (email.isBlank() || password.isBlank() ||
                    nickname.isBlank() || school.isBlank()
                ) {
                    errorMsg = "모든 필드를 입력해주세요"
                } else {
                    viewModel.register(
                        email = email,
                        password = password,
                        nickname = nickname,
                        school = school,
                        onSuccess = {
                            navController.navigate("login") {
                                popUpTo("register") { inclusive = true }
                            }
                        },
                        onError = { msg -> errorMsg = msg }
                    )
                }
            }
        ) {
            Text("회원가입 완료")
        }

        Spacer(Modifier.height(12.dp))

        if (errorMsg.isNotEmpty()) {
            Text(errorMsg, color = Color.Red)
        }
    }
}
