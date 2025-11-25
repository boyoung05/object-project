package com.example.book.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.book.Screens.Search.SearchScreen
import com.example.book.Screens.BookInfo.BookInfoScreen

@Composable
fun HomeScreen() { /* TODO: 홈 화면 UI */ }

@Composable
fun ChatScreen() { /* TODO: 채팅 목록 화면 */ }

@Composable
fun MyPageScreen() { /* TODO: 마이페이지 화면 */ }

@Composable
fun BottomNavHost(
    navController: NavHostController,
    paddingValues: PaddingValues
) {
    NavHost(
        navController = navController,
        startDestination = "home",
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        composable("home") { HomeScreen() }
        composable("search") { SearchScreen(navController) }
        composable("chat") { ChatScreen() }
        composable("mypage") { MyPageScreen() }

        composable("bookinfo") { BookInfoScreen() }
    }
}
