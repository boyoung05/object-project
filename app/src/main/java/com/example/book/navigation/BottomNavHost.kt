package com.example.book.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

import com.example.book.Screens.BookInfo.BookInfoScreen
import com.example.book.Screens.Search.SearchScreen
import com.example.book.Screens.home.HomeScreen
import com.example.book.Screens.mypage.MyPageScreen
import com.example.book.Screens.exchange.ExchangeProposalScreen
import com.example.book.Screens.chat.ChatRoomScreen
import com.example.book.Screens.chat.ChatScreen
import com.example.book.Screens.mypage.TradeListScreen
import com.example.book.Screens.mypage.UploadBookScreen
import com.example.book.Screens.mypage.ExchangeCountScreen

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

        // 1. 홈 화면
        composable("home") { HomeScreen(navController) }

        // 2. 검색 화면
        composable("search") { SearchScreen(navController) }

        // 3. 채팅 목록 화면
        composable("chat") { ChatScreen() }

        // 4. 마이페이지
        composable("mypage") { MyPageScreen(navController) }

        // 5. 책 상세
        composable(
            route = "bookinfo/{bookId}",
            arguments = listOf(
                navArgument("bookId") {type = NavType.IntType}
            )
        ) { backStackEntry ->
            val bookId = backStackEntry.arguments?.getInt("bookId") ?: return@composable

            BookInfoScreen(
                navController = navController,
                bookId = bookId
            )
        }

        // 6. 교환 제안 화면
        composable("exchange_proposal") { ExchangeProposalScreen(navController) }

        // 7. 채팅방
        composable("chat_room/{roomId}") { backStackEntry ->
            ChatRoomScreen()
        }

        // ⭐ 8. 거래 완료 리스트 화면
        composable("trade_list") {
            TradeListScreen()
        }

        composable("upload_book") { UploadBookScreen(navController) }

        composable("exchange_count") {
            ExchangeCountScreen()
        }


    }
}

/**
 * 책 상세 navigate 방법
 * route = "bookinfo/{bookId}"
 * navArgument("bookId") { type = NavType.StringType }
 * backStackEntry.arguments?.getString("bookId") 로 값 꺼내서 BookInfoScreen에 전달
 */
