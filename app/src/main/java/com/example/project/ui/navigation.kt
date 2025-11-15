package com.example.project.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

import com.example.project.ui.screen.home.HomeScreen

import com.example.project.ui.screen.chat.ChatScreen
import com.example.project.ui.screen.search.SearchScreen
import com.example.project.ui.screen.mypage.MyPageScreen

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Search : Screen("search")
    object Chat : Screen("chat")
    object MyPage : Screen("mypage")
}

@Composable
fun AppNavGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route) { HomeScreen(navController) }
        composable(Screen.Search.route) { SearchScreen() }
        composable(Screen.Chat.route) { ChatScreen() }
        composable(Screen.MyPage.route) { MyPageScreen() }
    }
}
