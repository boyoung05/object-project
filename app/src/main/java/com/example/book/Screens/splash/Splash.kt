package com.example.book.ui.screens.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.delay
import com.google.firebase.auth.FirebaseAuth
@Composable
fun SplashScreen(navController: NavController) {

    LaunchedEffect(Unit) {

        delay(1500)

        val user = FirebaseAuth.getInstance().currentUser

        if (user != null) {
            // 로그인 상태
            navController.navigate("main") {
                popUpTo("splash") { inclusive = true }
            }
        } else {
            // 로그아웃 상태 → 로그인 화면
            navController.navigate("login") {
                popUpTo("splash") { inclusive = true }
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        contentAlignment = Alignment.Center
    ) {
        Text("BOOK", fontSize = 32.sp, color = Color.White)
    }
}
