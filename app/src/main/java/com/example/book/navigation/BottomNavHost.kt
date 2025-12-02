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
import com.example.book.Screens.home.HomeScreen
import com.example.book.Screens.mypage.MyPageScreen
import com.example.book.Screens.exchange.ExchangeProposalScreen
import com.example.book.Screens.chat.ChatRoomScreen

@Composable
fun BottomNavHost(
    navController: NavHostController,
    rootNavController: NavHostController,    //  추가
    paddingValues: PaddingValues
) {
    NavHost(
        navController = navController,
        startDestination = "home",
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {

        composable("home") { HomeScreen(navController) }

        composable("search") { SearchScreen(navController) }

        composable("chat") { ChatRoomScreen() }

        //  MyPageScreen에는 rootNavController 전달
        composable("mypage") { MyPageScreen(rootNavController) }

// 책 상세
        composable("bookinfo/{bookId}") { backStackEntry ->
            val bookId = backStackEntry.arguments?.getString("bookId")?.toInt() ?: -1
            BookInfoScreen(navController, bookId)
        }

        composable("exchange_proposal") { ExchangeProposalScreen(navController) }
    }
}
