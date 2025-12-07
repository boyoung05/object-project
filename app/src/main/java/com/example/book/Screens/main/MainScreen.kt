package com.example.book.ui.screens.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.book.navigation.BottomNavHost
import com.example.book.ui.components.BottomNavBar

@Composable
fun MainScreen(rootNavController: NavHostController) {

    //  BottomNav 전용 컨트롤러 (메인 네비와 독립)
    val bottomNavController = rememberNavController()

    Scaffold(
        bottomBar = { BottomNavBar(bottomNavController) }
    ) { padding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            BottomNavHost(
                navController = bottomNavController,
                rootNavController = rootNavController,   //  반드시 전달
                paddingValues = padding
            )
        }
    }
}
