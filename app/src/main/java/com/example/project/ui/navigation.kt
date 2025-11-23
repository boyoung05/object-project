package com.example.project.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.project.ui.screen.home.HomeScreen

@Composable
fun AppNavGraph(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = "home"
    ) {

        // 홈 화면
        composable("home") {
            HomeScreen(navController = navController)
        }

        // 필요한 경우 추가될 화면들
        // composable("search") { SearchScreen(navController) }
        // composable("mypage") { MyPageScreen(navController) }
    }
}
