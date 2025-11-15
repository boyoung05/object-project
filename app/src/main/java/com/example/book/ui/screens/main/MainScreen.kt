package com.example.book.ui.screens.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.book.ui.components.BottomNavBar
import com.example.book.ui.screens.home.HomeScreen

@Composable
fun MainScreen() {
    val innerNavController: NavHostController = rememberNavController()

    Scaffold(
        bottomBar = { BottomNavBar(innerNavController) }
    ) { padding ->

        NavHost(
            navController = innerNavController,
            startDestination = "home",
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
        ) {
            composable("home") { HomeScreen() }
            // 앞으로 search/chat/mypage 추가 가능
        }
    }
}
