package com.example.book.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.book.Screens.BookInfo.BookInfoScreen
import com.example.book.Screens.Search.SearchScreen

// 너의 화면들
import com.example.book.navigation.HomeScreen
import com.example.book.navigation.ChatScreen
import com.example.book.navigation.MyPageScreen

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

        // 홈 화면
        composable("home") { HomeScreen() }

        // 검색 화면
        composable("search") { SearchScreen(navController) }

        // 채팅 화면
        composable("chat") { ChatScreen() }

        // 마이페이지 화면
        composable("mypage") { MyPageScreen() }

        // 책 상세
        composable("bookinfo") { BookInfoScreen() }
    }
}
