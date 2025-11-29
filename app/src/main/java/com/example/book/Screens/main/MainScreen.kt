package com.example.book.ui.screens.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.book.navigation.BottomNavHost
import com.example.book.ui.components.BottomNavBar

@Composable
fun MainScreen() {

    val bottomNavController = rememberNavController()

    Scaffold(
        bottomBar = { BottomNavBar(bottomNavController) }
    ) { padding ->

        Box(
            modifier = Modifier
                .fillMaxSize()     // ← 배경을 지정하지 않는다!!
        ) {
            BottomNavHost(
                navController = bottomNavController,
                paddingValues = padding
            )
        }
    }
}
