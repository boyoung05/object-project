package com.example.book.ui.screens.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.book.navigation.BottomNavHost
import com.example.book.ui.components.BottomNavBar
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.background

@Composable
fun MainScreen(navController: NavHostController) {

    Scaffold(
        bottomBar = { BottomNavBar(navController) }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color.Black)
        ) {
            // ⚠ 여기 절대 NavHost 넣지 마! (Crash 원인)
        }
    }
}

