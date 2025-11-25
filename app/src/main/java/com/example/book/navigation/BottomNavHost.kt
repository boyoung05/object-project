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

import com.example.book.navigation.HomeScreen
import com.example.book.navigation.ChatScreen
import com.example.book.navigation.MyPageScreen

import com.example.book.Screens.exchange.ExchangeProposalScreen
import com.example.book.Screens.chat.ChatRoomScreen   // ⭐ 추가

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

        // 1. 홈
        composable("home") { HomeScreen() }

        // 2. 검색 (navController 필요)
        composable("search") { SearchScreen(navController) }

        // 3. 채팅 목록
        composable("chat") { ChatScreen() }

        // 4. 마이페이지
        composable("mypage") { MyPageScreen() }

        // 5. 책 상세
        composable("bookinfo") { BookInfoScreen(navController) }

        // 6. 교환 제안 화면
        composable("exchange_proposal") { ExchangeProposalScreen(navController) }

        //  7. 챗룸 화면 (여기에 추가)
        composable("chat_room/{roomId}") { backStackEntry ->
            ChatRoomScreen()
        }
    }
}
